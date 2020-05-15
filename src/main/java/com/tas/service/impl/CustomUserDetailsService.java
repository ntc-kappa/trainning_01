package com.tas.service.impl;

import com.tas.dto.CustomUserDetails;
import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import com.tas.repository.RoleRepository;
import com.tas.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUsername(s);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }

        List<RoleEntity> roleEntities = roleRepository.findAllByIdUserEntity(userEntity.getId());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (RoleEntity roleEntity : roleEntities) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleEntity.getCode()));
        }
        CustomUserDetails userDetail = new CustomUserDetails(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                authorities);
        //Copy field same userEntity -> userDetail
        BeanUtils.copyProperties(userEntity, userDetail);

        return userDetail;
    }
}
