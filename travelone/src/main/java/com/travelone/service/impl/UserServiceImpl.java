package com.travelone.service.impl;

import com.travelone.dao.UserDao;
import com.travelone.dao.impl.UserDaoImpl;
import com.travelone.domain.ResultCode;
import com.travelone.domain.ResultInfo;
import com.travelone.domain.User;
import com.travelone.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public ResultInfo addUser(User user) {
        UserDao userDao = new UserDaoImpl();
        User userByName = userDao.findByUserName(user.getUsername());
        if(userByName ==null ){
            int result = userDao.addUser(user);
            if(result==1){
                return new ResultInfo(ResultCode.SUCCESS,user,"注册成功！");
            }else {
                return new ResultInfo(ResultCode.FAILURECODE,null,"注册失败！");
            }

        }else {
            return new ResultInfo(ResultCode.USEREXIST,null,ResultCode.USEREXISTMSG);
        }

    }

    @Override
    public User findUserByUsername(String username) {
        UserDao userDao = new UserDaoImpl();
        return userDao.findByUserName(username);
    }

    @Override
    public int updateUser(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.updateUser(user);

    }

    @Override
    public User findUserByCode(String code) {
        UserDao userDao = new UserDaoImpl();
        return userDao.findUserByCode(code);
    }

    @Override
    public int updateUserStatus(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.updateUserStatus(user);
    }

    @Override
    public ResultInfo login(String username, String password) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByUserName(username);
        if(user == null){
            return new ResultInfo(ResultCode.NOREGIST,null,ResultCode.NOREGISTMSG);
        }else {
            if(!user.getPassword().equals(password)){
                return new ResultInfo(ResultCode.PASSWORDNOEQUAL,null,ResultCode.PASSWORDNOEQUALMSG);
            }else {
                if(!"yes".equals(user.getStatus())){
                    return new ResultInfo(ResultCode.USERISNOACTIVE,null,ResultCode.USERISNOACTIVEMSG);
                }else {
                    return new ResultInfo(ResultCode.SUCCESS,null,"登录成功");
                }
            }
        }
    }
}
