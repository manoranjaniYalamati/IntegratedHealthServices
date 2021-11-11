package com.sai.services;

import com.sai.model.Role;

public interface RoleService {
    Role findByName(String name);

    Role findRoleById(Long id);
}
