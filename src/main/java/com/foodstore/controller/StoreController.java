package com.foodstore.controller;

import com.foodstore.entity.Food;
import com.foodstore.service.FoodService;
import com.foodstore.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    
    @RequestMapping("/")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            model.addAttribute("login_btn", "/logout");
            model.addAttribute("login_text", "退出");
            String userInfo = "欢迎，" + subject.getPrincipal().toString();
            if (subject.hasRole("vip")) userInfo += "（VIP用户）"; else userInfo += "（普通用户）";
            model.addAttribute("user_info", userInfo);
        } else {
            model.addAttribute("login_btn", "/login");
            model.addAttribute("login_text", "登录");
        }
        return "index";
    }
    
    @RequestMapping("/menu")
    public String menu(Model model) {
        Subject subject = SecurityUtils.getSubject();
        List<Food> allFood = deepCopy(foodService.findAll());
        if (subject.isAuthenticated() || subject.isRemembered()) {
            model.addAttribute("login_btn", "/logout");
            model.addAttribute("login_text", "退出");
            String userInfo = "欢迎，" + subject.getPrincipal().toString();
            if (subject.hasRole("vip")) userInfo += "（VIP用户）"; else userInfo += "（普通用户）";
            model.addAttribute("user_info", userInfo);
            model.addAttribute("login_show", "display:none;");
            if (!subject.hasRole("vip")) model.addAttribute("vip_hidden", "display:none;");
            else {
                for (int i = 0; i < 9; i++) {
                    if (allFood.get(i).getIsvip())
                        allFood.get(i).setPrice(String.format("%.2f", Double.parseDouble(allFood.get(i).getPrice()) * 0.95));
                }
            }
        } else {
            model.addAttribute("login_btn", "/login");
            model.addAttribute("login_text", "登录");
            model.addAttribute("pay_show", "display:none;");
            model.addAttribute("vip_hidden", "display:none;");
        }
        model.addAttribute("food", allFood);
        return "menu";
    }

    @RequestMapping("/cuisine_detail/{name}")
    public String detail(@PathVariable String name, Model model) {
        int id = name.charAt(name.length() - 1) - '0';
        List<Food> allFood = deepCopy(foodService.findAll());
        Food curFood = allFood.get(id - 1);
        String curDiscount = "无";
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            model.addAttribute("login_btn", "/logout");
            model.addAttribute("login_text", "退出");
            String userInfo = "欢迎，" + subject.getPrincipal().toString();
            if (subject.hasRole("vip")) userInfo += "（VIP用户）"; else userInfo += "（普通用户）";
            model.addAttribute("user_info", userInfo);
            if (!subject.hasRole("vip")) model.addAttribute("vip_hidden", "display:none;");
            else {
                if (curFood.getIsvip()) {
                    curFood.setPrice(String.format("%.2f", Double.parseDouble(curFood.getPrice()) * 0.95));
                    curDiscount = "9.5折";
                }
            }
        } else {
            model.addAttribute("login_btn", "/login");
            model.addAttribute("login_text", "登录");
            model.addAttribute("vip_hidden", "display:none;");
        }
        model.addAttribute("all_food", allFood);
        model.addAttribute("food_detail", curFood);
        model.addAttribute("food_pic", "n" + id + ".jpg");
        model.addAttribute("discount", curDiscount);
        return "detail";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam String amount) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            String curName = subject.getPrincipal().toString();
            Long id = orderService.submitOrder(curName, amount);
            return "redirect:/order/" + id;
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/order/{str}")
    public String order(@PathVariable String str, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            if (!isNumber(str)) return "redirect:/";
            String userInfo = "欢迎，" + subject.getPrincipal().toString();
            if (subject.hasRole("vip")) userInfo += "（VIP用户）"; else userInfo += "（普通用户）";
            model.addAttribute("user_info", userInfo);
            return "order";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login_GET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login_POST(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if (remember != null) token.setRememberMe(true);
        String msg = null;

        try{
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = "用户名不存在";
        } catch (IncorrectCredentialsException e) {
            msg = "密码错误";
        } catch (AuthenticationException e) {
            msg = "其他错误：" + e.getMessage();
        }

        if (msg != null) {
            model.addAttribute("msg", msg);
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String user_logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }

    private boolean isNumber(String str) {
        int len = str.length();
        if (len == 0) return false;
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private List<Food> deepCopy(List<Food> data) {
        List<Food> tmp = new ArrayList<Food>();
        for (Food food : data) {
            tmp.add(new Food(food));
        }
        return tmp;
    }
}
