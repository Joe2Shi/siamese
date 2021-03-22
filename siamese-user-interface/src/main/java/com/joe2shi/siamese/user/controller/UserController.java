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
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckBo check) {
        return ResponseEntity.ok(userServiceProxy.validation(check));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterBo register) {
        return ResponseEntity.ok(userServiceProxy.register(register));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditBo accredit) {
        return ResponseEntity.ok(userServiceProxy.accredit(accredit));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user() {
        return ResponseEntity.ok(userServiceProxy.user());
    }
}
