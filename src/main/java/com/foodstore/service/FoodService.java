package com.foodstore.service;

import com.foodstore.entity.Food;

import java.util.List;

public interface FoodService {

    public Food createFood(Food food);
    public Food updateFood(Food food);
    public void deleteFood(Long foodId);
    public Food findOne(Long foodId);
    public List<Food> findAll();
}
