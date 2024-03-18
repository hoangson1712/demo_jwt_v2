package com.example.demojwt.filter;

import com.example.demojwt.payload.RoleResponse;
import com.example.demojwt.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    private Gson gson = new Gson();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.trim().length() > 0){
            String token = headerAuth.substring(7);
            // Giải mã token
            String data = jwtUtils.decryptToken(token);
            if (data != null){
                RoleResponse roleResponse = gson.fromJson(data, RoleResponse.class);
                System.out.println("kiem tra jwt data " + data + " - " + roleResponse.getName());

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleResponse.getName());
                authorityList.add(simpleGrantedAuthority);

                // Tạo chứng thực cho security biết là đã hợp lệ và bypass được tất cả các filter của security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken("","",authorityList);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(auth);
            }

        }
        filterChain.doFilter(request,response);
    }
}
