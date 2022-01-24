package com.yrkim.springsecurity.service.impl;

import com.yrkim.springsecurity.exceptions.UserNotFoundException;
import com.yrkim.springsecurity.model.dto.UserDTO;
import com.yrkim.springsecurity.model.entity.AuthRole;
import com.yrkim.springsecurity.model.entity.User;
import com.yrkim.springsecurity.repository.jpa.AuthRoleRepository;
import com.yrkim.springsecurity.repository.jpa.UserRepositoy;
import com.yrkim.springsecurity.response.ListResult;
import com.yrkim.springsecurity.response.SingleResult;
import com.yrkim.springsecurity.service.ResponseService;
import com.yrkim.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepositoy userRepository;
    private final ResponseService responseService;
    private final AuthRoleRepository authRoleRepository;

    /*
    *
    * */
    @Override
    public SingleResult<UserDTO> selectUser(long id) {
        return responseService.getSingleResult(new UserDTO(userRepository.findById(id).orElseThrow(UserNotFoundException::new)));
    }

    @Override
    public ListResult<UserDTO> selectUsers(Pageable pageable) {
        Page<User> userDTOS = userRepository.findAll(pageable);

        return getUserDtoList(pageable , userDTOS);
    }

    private ListResult<UserDTO> getUserDtoList(Pageable pageable , Page<User> users) {
        List<UserDTO> userDTOS = users.getContent().stream()
            .map(user -> new UserDTO(user))
            .collect(Collectors.toList());
        Page<UserDTO> userDTOPage = new PageImpl<>(userDTOS , pageable , users.getTotalElements());
        return responseService.getListResult(userDTOPage);
    }

    @Transactional
    @Override
    public void insertUser(UserDTO userDTO){

        Optional<AuthRole> role = Optional.of(authRoleRepository.findByRoleName("ROLE_USER")).get();
        Set<AuthRole> roles = new HashSet<>();
        roles.add(role.get());

        userDTO.setRoles(roles.stream().map(AuthRole::getRoleName).collect(Collectors.toSet()));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userDTO.toUser(userDTO);

        userRepository.save(user);
    }
}
