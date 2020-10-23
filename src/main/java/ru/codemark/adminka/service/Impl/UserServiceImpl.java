package ru.codemark.adminka.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.codemark.adminka.model.Role;
import ru.codemark.adminka.model.User;
import ru.codemark.adminka.repo.UserRepo;
import ru.codemark.adminka.respons.Respons;
import ru.codemark.adminka.service.RoleService;
import ru.codemark.adminka.service.UserService;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll().stream()
                .peek(u -> u.setRoles(new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @Override
    public User findUserByLogin(String login) {
        User userDTO = userRepo.findById(login).orElse(new User(""));
        if (userDTO.getLogin().isEmpty()) {
            return userDTO;
        }
        List<Role> roles = userDTO.getRoles().stream()
                .peek(r -> r.setUsers(new ArrayList<>()))
                .collect(Collectors.toList());
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public Respons deleteUser(User user) {
        Respons respons = new Respons();
        User userDTO = findUserByLogin(user.getLogin());
        if (!userDTO.getLogin().isEmpty()) {
            respons.setSuccess(true);
            userRepo.delete(user);
            return respons;
        }
        respons.setSuccess(false);
        respons.setErrors(Collections.singletonList("user not found"));
        return respons;
    }

    @Override
    public Respons addUser(User user) {
        User userDTO = findUserByLogin(user.getLogin());
        if (!userDTO.getLogin().isEmpty()) {
            return new Respons(false, Collections.singletonList("there is such a user"));
        }
        List<Role> roles = user.getRoles().stream()
                .filter(r -> {
                    Role role = roleService.findRole(r.getName());
                    return !role.getName().isEmpty();
                })
                .map(r -> roleService.findRole(r.getName()))
                .collect(Collectors.toList());
        user.setRoles(roles);
        try {
            userRepo.save(user);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return new Respons(false);

        }
        return  new Respons(true);
    }

    @Override
    public Respons update(User user) {
        User userDTO = findUserByLogin(user.getLogin());
        if (userDTO.getLogin().isEmpty()) {
            return new Respons(false, Collections.singletonList("user not found"));
        }
        List<Role> roles = user.getRoles().stream()
                .filter(r -> {
                    Role role = roleService.findRole(r.getName());
                    return !role.getName().isEmpty();
                })
                .map(r -> roleService.findRole(r.getName()))
                .collect(Collectors.toList());
        userDTO.setPass(user.getPass());
        userDTO.setName(user.getName());
        userDTO.setRoles(roles);
        userRepo.save(userDTO);
        return new Respons(true);
    }

}
