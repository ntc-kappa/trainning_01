package com.tas.service;

import com.tas.entity.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> getAllEntity();
    public List<UserEntity> getAllEntity(int numOnPage);
    public UserEntity findById(Integer id);
    public void save(UserEntity userEntity);
    public long getCount();
    public boolean delete(Integer id);
    public long deleteMany(Integer[] ids);

}
