package com.joe2shi.siamese.auth.controller;

import com.joe2shi.siamese.auth.dto.AccreditDto;
import com.joe2shi.siamese.auth.service.AuthService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@SuppressWarnings("rawtypes")
public class AuthController {
    @Resource
    private AuthService authService;

    @PostMapping("accredit")
    public ResponseEntity<SiameseResult> accredit(@RequestBody AccreditDto accredit) {
        return ResponseEntity.ok(authService.accredit(accredit));
    }
}
