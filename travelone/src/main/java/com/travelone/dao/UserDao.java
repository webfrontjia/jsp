package com.travelone.dao;

import com.travelone.domain.User;

public interface UserDao {
    public User findByUserName(String username);

    public int addUser(User user);

    public int updateUser(User user);

    public User findUserByCode(String code);

    public int updateUserStatus(User user);

}
