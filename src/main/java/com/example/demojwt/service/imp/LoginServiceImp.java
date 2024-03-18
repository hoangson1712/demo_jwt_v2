package com.example.demojwt.service.imp;

import com.example.demojwt.entity.UserEntity;
import com.example.demojwt.payload.RoleResponse;
import com.example.demojwt.repository.UserRepository;
import com.example.demojwt.service.LoginService;
import com.example.demojwt.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username, String password) {

        String token = "";
        UserEntity userEntity = userRepository.findByEmail(username);
        if (passwordEncoder.matches(password,userEntity.getPassword())){
            // Tạo token từ key đã sinh ra và lưu trữ trước đó
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setName(userEntity.getRoleId().getName());
            String roles = gson.toJson(roleResponse);
            token = jwtUtils.createToken(roles);
        }
        return token;
    }
}
