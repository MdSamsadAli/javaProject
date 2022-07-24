package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {


    Role addRole(Role role);

    List<Role> getAllRoles();

    Role findById(int id);

    Role updateRole(Role role);

    String deleteRole(int id);
}
