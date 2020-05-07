package com.tas.service.impl;

import com.tas.converter.UserConverter;
import com.tas.dto.UserDto;
import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import com.tas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public List<UserDto> getAllEntity(){
        List<UserEntity> entities=userRepository.findAll();
        List<UserDto> dtos=userConverter.toListDto(entities);
        return dtos;
    }

    @Override
    public List<UserEntity> getAllEntity(int numOnPage) {
        Pageable pageable = new PageRequest(numOnPage, 4);
        List<UserEntity> userEntities=userRepository.findAll(pageable).getContent();

        return userEntities;
    }

    public UserDto findById(Integer id){
        UserEntity user=userRepository.findById(id);
        UserDto dto=userConverter.toDto(user);
        return dto;
    }

    @Override
    public void save(UserDto dto) {
        userRepository.save(userConverter.toEntity(dto));
    }



    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null)

            return false;

        try {

            userRepository.delete(id);

            return true;

        } catch (Exception ex) {

            return false;

        }
    }
    @Override
    public long deleteMany(Integer[] ids) {

        try {

            userRepository.deleteSomeUser(ids);

            return ids.length;

        } catch (Exception ex) {

            return 0;

        }

    }

    @Override
    public boolean findByUserName(String username) {
        try {

            UserEntity user=userRepository.findByUsername(username);

            if(user ==null) {
                return true;
            } else return false;

        } catch (Exception ex) {

            return false;

        }
    }

    @Override
    public boolean findEmail(String email) {
        try {

            UserEntity user=userRepository.findByEmail(email);


            if(user ==null) {
                return true;
            } else return false;

        } catch (Exception ex) {

            return false;

        }
    }


}

