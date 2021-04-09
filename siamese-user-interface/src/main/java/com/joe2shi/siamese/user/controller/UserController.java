package com.joe2shi.siamese.user.controller;

import com.joe2shi.siamese.user.dto.CheckDto;
import com.joe2shi.siamese.user.dto.RegisterDto;
import com.joe2shi.siamese.user.dto.AccreditDto;
import com.joe2shi.siamese.user.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@SuppressWarnings("rawtypes")
public class UserController {
    @Resource
    private UserServiceProxy userServiceProxy;

    @PostMapping("validation")
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckDto check) {
        return ResponseEntity.ok(userServiceProxy.validation(check));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterDto register) {
        return ResponseEntity.ok(userServiceProxy.register(register));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditDto accredit) {
        return ResponseEntity.ok(userServiceProxy.accredit(accredit));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user() {
        return ResponseEntity.ok(userServiceProxy.user());
    }
}
