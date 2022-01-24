package com.yrkim.springsecurity.service;

import com.yrkim.springsecurity.model.dto.UserDTO;
import com.yrkim.springsecurity.response.ListResult;
import com.yrkim.springsecurity.response.SingleResult;
import org.springframework.data.domain.Pageable;

public interface UserService {
    SingleResult<UserDTO> selectUser(long id);
    ListResult<UserDTO> selectUsers(Pageable pageable);
    void insertUser(UserDTO userDTO);
}
