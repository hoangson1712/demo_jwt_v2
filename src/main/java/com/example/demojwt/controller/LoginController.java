package com.example.demojwt.controller;

import com.example.demojwt.payload.BaseResponse;
import com.example.demojwt.service.imp.LoginServiceImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     *  Do password lữu trữ trong database là chuỗi mã hóa dạng Bcrypt cho nên không thể
     *  dùng password như điều kiện WHERE
     *
     *  Bước 1: Viết câu truy vấn lấy dữ liệu đăng nhập dựa trên username
     *  Bước 2: Lấy dữ liệu password trả ra từ bước 1 và kiểm tra xem password lưu trữ trong
     *  database với password người dùng truyền lên
     *  Bước 3: Nếu 2 mật khẩu match thì sẽ tạo ra token, nếu ko giống thì báo đăng nhập thất bại
     */

    @Autowired
    private LoginServiceImp loginServiceImp;
    @Autowired
    private BaseResponse baseResponse;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestParam String username,@RequestParam String password){
        String token = loginServiceImp.checkLogin(username, password);
        baseResponse.setData(token);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
