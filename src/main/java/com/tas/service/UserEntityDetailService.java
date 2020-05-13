package com.tas.service;

import com.tas.common.Loggable;
import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import com.tas.repository.RoleRepository;
import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class UserEntityDetailService implements UserDetailsService , Loggable {
    @Autowired(required = true)
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        UserEntity entity=null;
        try {
             entity  = userRepository.findOneByUsername(s);
        }catch (Exception e){
            e.printStackTrace();

        }

        if (entity == null) {

            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }

        Set<RoleEntity> listRoles = entity.getRoleEntities();
        for (RoleEntity roleEntity: listRoles
             ) {
            getLogger().info(roleEntity.getName());
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
            grantList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserDetails userDetails= (UserDetails) new User(entity.getUsername(),entity.getPassword(),entity.isActive(),true,true,true,grantList);

        return userDetails;
    }
}
