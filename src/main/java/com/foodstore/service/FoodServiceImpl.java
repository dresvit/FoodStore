package com.foodstore.service;

import com.foodstore.dao.FoodDao;
import com.foodstore.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDao foodDao;

    public FoodDao getFoodDao() {
        return foodDao;
    }

    public void setFoodDao(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Caching(put = @CachePut(value="foodCache", key="'food'+#food.id"),
             evict = @CacheEvict(value="foodCache", key="'findAllFood'"))
    public Food createFood(Food food) {
        return foodDao.createFood(food);
    }

    @Caching(put = @CachePut(value="foodCache", key="'food'+#food.id"),
             evict = @CacheEvict(value="foodCache", key="'findAllFood'"))
    public Food updateFood(Food food) {
        return foodDao.updateFood(food);
    }

    @Caching(evict = {@CacheEvict(value="foodCache", key="'food'+#foodId"),
                      @CacheEvict(value="foodCache", key="'findAllFood'")})
    public void deleteFood(Long foodId) {
        foodDao.deleteFood(foodId);
    }

    @Cacheable(value="foodCache", key="'food'+#foodId")
    public Food findOne(Long foodId) {
        return foodDao.findOne(foodId);
    }

    @Cacheable(value="foodCache", key="'findAllFood'")
    public List<Food> findAll() {
        return foodDao.findAll();
    }
}
