package com.joe2shi.siamese.user.controller;

import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.user.dto.CheckDto;
import com.joe2shi.siamese.user.dto.RegisterDto;
import com.joe2shi.siamese.user.dto.AccreditDto;
import com.joe2shi.siamese.user.service.UserService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@SuppressWarnings("rawtypes")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("validation")
    public ResponseEntity<SiameseResult> validation(@RequestBody CheckDto check) {
        return ResponseEntity.ok(userService.validation(check));
    }

    @PostMapping("register")
    public ResponseEntity<SiameseResult> register(@RequestBody RegisterDto register) {
        return ResponseEntity.ok(userService.register(register));
    }

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditDto accredit) {
        return ResponseEntity.ok(userService.accredit(accredit));
    }

    @GetMapping("user")
    public ResponseEntity<SiameseResult> user(HttpServletRequest request) {
        return ResponseEntity.ok(userService.user(request.getHeader(SystemConstant.STRING_ID)));
    }
}
