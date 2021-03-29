package com.joe2shi.siamese.user.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
import com.joe2shi.siamese.user.service.UserService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@SuppressWarnings("rawtypes")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("validation")
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckBo check) {
        return ResponseEntity.ok(userService.validation(check));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterBo register) {
        return ResponseEntity.ok(userService.register(register));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditBo accredit) {
        return ResponseEntity.ok(userService.accredit(accredit));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user(HttpServletRequest request) {
        return ResponseEntity.ok(userService.user(request.getHeader(SystemConstant.STRING_ID)));
    }

    @GetMapping("tx-manage")
    @LcnTransaction
    @Transactional
    public ResponseEntity<SiameseResult> txManage() {
        RegisterBo registerBo = new RegisterBo();
        registerBo.setUsername("Admin");
        registerBo.setPhoneNumber("15291379291");
        registerBo.setPassword("19990628sq");
        return ResponseEntity.ok(userService.register(registerBo));
    }
}
