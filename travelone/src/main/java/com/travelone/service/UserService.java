package com.travelone.service;

import com.travelone.domain.ResultInfo;
import com.travelone.domain.User;
import org.omg.CORBA.PUBLIC_MEMBER;

public interface UserService {
    public ResultInfo addUser(User user);

    public User findUserByUsername(String username);

    public int updateUser(User user);

    public User findUserByCode(String code);

    public int updateUserStatus(User user);

    public ResultInfo login(String username, String password);
}
