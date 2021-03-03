package com.joe2shi.siamese.auth.controller;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.auth.service.UserService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("auth")
@CrossOrigin
@SuppressWarnings("rawtypes")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterBo registerBo) {
        return ResponseEntity.ok(userService.register(registerBo));
    }

    @PostMapping("signin")
    public ResponseEntity<SiameseResult> signIn(@RequestBody SignInBo signInBo) {
        return ResponseEntity.ok(userService.signIn(signInBo));
    }
}
