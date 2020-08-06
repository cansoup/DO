package com.backend.controller;

import com.backend.service.JwtService;
import com.backend.service.UserService;
import com.backend.dto.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.awt.SystemColor.info;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody User user, HttpServletResponse res){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try{
            User loginUser = service.signin(user.getEmail(), user.getPassword());
            // 로그인 성공했다면 토큰을 생성한다.
            String token = jwtService.create(loginUser);
            // 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아둔다.
//            res.setHeader("jwt-auth-token", token);
            resultMap.put("jwt-auth-token", token);
            resultMap.put("status", true);
            resultMap.put("request_body", user);
            status = HttpStatus.ACCEPTED;
        } catch(RuntimeException e){
            log.error("정보조회 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    /**
     *
     * @param loginVo email을 보낸다.
     * @return True : Email을 만들 수 있을 경우
     *          False : Email을 만들 수 없을 경우
     */

    @PostMapping("/api/emailduplicate")
    public boolean isemailAvailable (@RequestBody User  loginVo) {    //HTTP요청의 내용을 객체에 매핑하기 위해 @RequestBody 를 설정.
        // @Controller인 경우 @ResponseBody를 적어야한다.
        String in_email = loginVo.getEmail();

        User memberVo = service.findByEmail(in_email);    //UserID로 user가 존재하는지 확인.
        if (memberVo == null) {
            return true;
        }else if(memberVo != null){
            return false;
        }
        return false;
    }

    // 회원가입
    // 회원가입 정상 동작 >> return "회원가입 완료"
    // 실패 >> return ERROR MSG
    @PostMapping("/api/v1")
    public ResponseEntity<?> signup(@RequestBody User user)
    {
        System.out.println(user.toString());
        try{
            service.save(user);
            return new ResponseEntity<>("회원가입 완료",HttpStatus.OK);
        }catch(Exception err){
            String errorMessage;
            errorMessage = err + " <== error ";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원정보확인
    // 회원정보확인 정상 동작 >> return "User값"
    // 실패 >> return null
    @GetMapping("/api/v1/{uid}")
    public Optional<User> findbyUid(@PathVariable String uid){
        return Optional.ofNullable(service.findByUid(uid));
    }

    // 회원탈퇴
    // 회원탈퇴 정상 동작 >> return "회원탈퇴 성공"
    // 실패 >> return ERROR MSG
    @DeleteMapping("/api/v1/{uid}")
    public ResponseEntity<?> delete(@PathVariable String uid) {
        try{
            service.delete(uid);
            return new ResponseEntity<>("회원탈퇴 성공", HttpStatus.OK);
        }catch(Exception err){
            String errorMessage;
            errorMessage = err + "<== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원정보수정
    // 회원정보수정 성공 >> return "회원수정 성공"
    // 실패 >> return ERROR MSG
    @PutMapping(value="/api/v1")
    public ResponseEntity<?> update(@RequestBody User user) {
        try{
            service.update(user);
            return new ResponseEntity<>("회원정보수정 성공", HttpStatus.OK);
        }catch(Exception err){
            String errorMessage;
            errorMessage = err + "<== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}