package com.yrkim.springsecurity.security;

import com.yrkim.springsecurity.exceptions.UserNotFoundException;
import com.yrkim.springsecurity.model.dto.UserDTO;
import com.yrkim.springsecurity.model.entity.User;
import com.yrkim.springsecurity.repository.jpa.UserRepositoy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoy userRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final String banIp = request.getRemoteAddr();

        Optional<User> user = Optional.of(userRepository.findByUsername(username))
                .orElseThrow(UserNotFoundException::new);

        if(user.isEmpty()) {
            if(userRepository.countByUsername(username) == 0) {
                throw new UserNotFoundException(String.format("%s","No User Found With Username : "+username));
            }
        }

        return getCustomUserDetails(user.get());
    }

    private CustomUserDetails getCustomUserDetails(User user) {
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
