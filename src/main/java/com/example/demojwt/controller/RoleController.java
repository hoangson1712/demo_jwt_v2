package com.example.demojwt.controller;

import com.example.demojwt.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleServiceImp.getAllRoles());
    }
    @PostMapping("")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Ok test");
    }
    @PutMapping("")
    public ResponseEntity<?> test1(){
        return ResponseEntity.ok("Ok 1");
    }
}
