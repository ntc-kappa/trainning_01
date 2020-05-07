package com.tas.config;

import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<UserEntity> {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findOneByUsername(username);
    }

}
