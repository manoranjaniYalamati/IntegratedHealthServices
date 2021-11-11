package com.sai.repository;

import com.sai.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
    Role findRoleById(Long id);
}