package com.sai.services;

import com.sai.model.Role;
import com.sai.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }

    @Override
    public Role findRoleById(Long id){
        Role role = roleDao.findRoleById(id);
        return role;
    }
}
