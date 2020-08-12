package com.backend.service.impl;

import com.backend.service.UserService;
import com.backend.dto.user.User;
import com.backend.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao dao;

    @Override
    public void save(User c) {
        dao.save(c);
    }

    @Override
    public void delete(String uid) {
        dao.delete(uid);
    }

    @Override
    public User findByUid(String uid) {
        return dao.findByUid(uid);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public void updateProfileImage(String email, String profileImage) {
        dao.updateProfileImage(email, profileImage);
    }

    @Override
    public void updateIntroduce(String email, String nickname, String introduce) {
        dao.updateIntroduce(email, nickname, introduce);
    }

    @Override
    public void updateSNS(String email, String facebook, String github, String instagram) {
        dao.updateSNS(email, facebook, github, instagram);
    }

    @Override
    public void updateQR(String email, String qrImage) {
        dao.updateQR(email, qrImage);
    }


    @Override
    public User signin(String email, String password) {
        // 이메일과 패스워드 DB와 비교.
        User user = dao.signin(email, password);

        if (user != null) {
            return dao.signin(email, password);
        } else {
            throw new RuntimeException("그런 사람은 없어요.");
        }
    }

}
