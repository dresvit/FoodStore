package com.foodstore.dao;

import com.foodstore.entity.User;

import java.util.Set;

public interface UserDao {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(String username);
    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);
    public User findOne(Long userId);
    public User findByUsername(String username);
    public Set<String> findRoles(String username);
}
