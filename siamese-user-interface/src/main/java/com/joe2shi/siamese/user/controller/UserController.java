package com.joe2shi.siamese.user.controller;

import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
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
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckBo checkBo) {
        return ResponseEntity.ok(userServiceProxy.validation(checkBo));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterBo registerBo) {
        return ResponseEntity.ok(userServiceProxy.register(registerBo));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditBo accreditBo) {
        return ResponseEntity.ok(userServiceProxy.accredit(accreditBo));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user() {
        return ResponseEntity.ok(userServiceProxy.user());
    }
}
