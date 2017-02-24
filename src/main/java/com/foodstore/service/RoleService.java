package com.foodstore.service;

import com.foodstore.entity.Role;

public interface RoleService {

    public Role createRole(Role role);
    public void deleteRole(Long roleId);
}
