package com.joe2shi.siamese.user.controller;

import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
import com.joe2shi.siamese.user.service.UserService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@SuppressWarnings("rawtypes")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("validation")
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckBo checkBo) {
        return ResponseEntity.ok(userService.validation(checkBo));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterBo registerBo) {
        return ResponseEntity.ok(userService.register(registerBo));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditBo accreditBo) {
        return ResponseEntity.ok(userService.accredit(accreditBo));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user(HttpServletRequest request) {
        return ResponseEntity.ok(userService.user(request.getHeader(SystemConstant.STRING_ID)));
    }
}
