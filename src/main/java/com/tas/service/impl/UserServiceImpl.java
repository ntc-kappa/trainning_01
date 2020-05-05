package com.tas.service.impl;

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

    public List<UserEntity> getAllEntity(){
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getAllEntity(int numOnPage) {
        Pageable pageable = new PageRequest(numOnPage, 4);
        List<UserEntity> userEntities=userRepository.findAll(pageable).getContent();

        return userEntities;
    }

    public UserEntity findById(Integer id){
        return userRepository.findById(id);
    }
    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
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


}

