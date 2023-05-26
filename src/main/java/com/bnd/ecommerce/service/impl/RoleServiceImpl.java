package com.bnd.ecommerce.service.impl;

import com.bnd.ecommerce.entity.Role;
import com.bnd.ecommerce.exception.ResourceNotFoundException;
import com.bnd.ecommerce.repository.RoleRepository;
import com.bnd.ecommerce.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository repository) {
    this.roleRepository = repository;
  }

  @Override
  public Role save(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public List<Role> listRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Role findById(int id) {
    Optional<Role> role = roleRepository.findById(id);
    if (role.isPresent()) return role.get();
    else throw new ResourceNotFoundException("Role Not Found");
  }

  @Override
  public Role findByRoleName(String roleName) {
    return roleRepository.findByNameIgnoreCase(roleName);
  }

  @Override
  public boolean deleteById(int id) {
    roleRepository.deleteById(id);
    return !roleRepository.existsById(id);
  }
}
