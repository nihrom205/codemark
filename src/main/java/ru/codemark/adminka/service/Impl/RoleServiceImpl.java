package ru.codemark.adminka.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.codemark.adminka.model.Role;
import ru.codemark.adminka.repo.RoleRepo;
import ru.codemark.adminka.service.RoleService;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findRole(String nameRole) {
        Optional<Role> roleOptional = roleRepo.findByName(nameRole);
        return roleOptional.orElseGet(() -> new Role(""));
    }
}
