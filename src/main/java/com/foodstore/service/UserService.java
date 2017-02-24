package com.foodstore.service;

import com.foodstore.entity.User;

import java.util.Set;

public interface UserService {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(String username);
    public User changePassword(String username, String newPassword);
    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);
    public User findByUsername(String username);
    public Set<String> findRoles(String username);
}
