package com.tas.service;

import com.tas.dto.UserDto;
import com.tas.entity.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllEntity();
    public List<UserEntity> getAllEntity(int numOnPage);
    public UserDto findById(Integer id);
    public void save(UserDto dto);
    public long getCount();
    public boolean delete(Integer id);
    public long deleteMany(Integer[] ids);
    public boolean findByUserName(String username);
    public boolean findEmail(String email);

}
