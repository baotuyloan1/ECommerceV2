package com.bnd.ecommerce.service;

import com.bnd.ecommerce.entity.Role;
import java.util.List;

public interface RoleService {

    Role save(Role role);

    List<Role> listRoles();

    Role findById(int id);

    Role findByRoleName(String roleName);

    boolean deleteById(int id);


}
