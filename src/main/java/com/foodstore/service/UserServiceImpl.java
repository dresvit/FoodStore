package com.foodstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.foodstore.dao.UserDao;
import com.foodstore.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @CachePut(value="userCache", key="#user.username")
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @CachePut(value="userCache", key="#user.username")
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @CacheEvict(value="userCache", key="#username")
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    @CachePut(value="userCache", key="#username")
    public User changePassword(String username, String newPassword) {
        User user = userDao.findByUsername(username);
        user.setPassword(newPassword);
        return userDao.updateUser(user);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        userDao.correlationRoles(userId, roleIds);
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        userDao.uncorrelationRoles(userId, roleIds);
    }

    @Cacheable(value="userCache", key="#username")
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }
}
