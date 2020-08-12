package com.backend.service;

import com.backend.dto.user.User;

public interface UserService{

    public void save(User c); // 회원 가입
    public void delete(String uid); // 회원 탈퇴
    public User findByUid(String uid); // 회원 정보 확인
    public User findByEmail(String email); // 회원 정보 확인


    public void updateProfileImage(String email, String profileImage); // profileImage 수정
    public void updateIntroduce(String email, String nickname, String introduce); // nickname, introduce 수정
    public void updateSNS(String email, String facebook, String github, String instagram); // facebook, github, instagram 수정
    public void updateQR(String email, String qrImage); // qrImage 수정

    public User signin(String email, String password);

}
