package com.backend.dao.impl;

import com.backend.dao.UserDao;
import com.backend.mapper.UserMapper;
import com.backend.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserMapper mapper;

    @Override
    public void save(User c) {

        mapper.save(c);
    }

    @Override
    public void delete(String uid) {
        mapper.delete(uid);
    }

    @Override
    public User findByUid(String uid) {
        return mapper.findByUid(uid);
    }

    @Override
    public User findByEmail(String email) {
        return mapper.findByEmail(email);
    }

    @Override
    public void updateProfileImage(String email, String profileImage) {
        mapper.updateProfileImage(email, profileImage);
    }

    @Override
    public void updateIntroduce(String email, String nickname, String introduce) {
        mapper.updateIntroduce(email, nickname, introduce);
    }

    @Override
    public void updateSNS(String email, String facebook, String github, String instagram) {
        mapper.updateSNS(email, facebook, github, instagram);
    }

    @Override
    public void updateQR(String email, String qrImage) {
        mapper.updateQR(email, qrImage);
    }

    @Override
    public User signin(String email, String password) {
        return mapper.signin(email, password);
    }

}
