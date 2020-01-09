package com.travelone.dao.impl;

import com.travelone.dao.UserDao;
import com.travelone.domain.User;
import com.travelone.utils.JdbcUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public User findByUserName(String username) {
        String sql = "select * from tab_user where username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,  user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday()
                , user.getSex(), user.getTelephone(), user.getEmail());

    }

    @Override
    public int updateUser(User user) {
        String sql = "update tab_user set code = ? where uid = ?";
        return jdbcTemplate.update(sql, user.getCode(), user.getUid());
    }

    @Override
    public User findUserByCode(String code) {
        String sql = "select * from tab_user where code = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
    }

    @Override
    public int updateUserStatus(User user) {
        String sql = "update tab_user set status = ? where uid = ?";
        return jdbcTemplate.update(sql,user.getStatus(),user.getUid());
    }
}
