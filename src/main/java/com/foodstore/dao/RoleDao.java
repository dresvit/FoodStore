package com.foodstore.dao;

import com.foodstore.entity.Role;

public interface RoleDao {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);
}
