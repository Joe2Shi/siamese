package com.joe2shi.siamese.auth.service.impl;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.auth.proxy.UserServiceProxy;
import com.joe2shi.siamese.auth.service.UserService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings("rawtypes")
public class UserServiceImpl implements UserService {
    @Resource
    private UserServiceProxy userServiceProxy;

    @Override
    public SiameseResult register(RegisterBo registerBo) {
        return userServiceProxy.register(registerBo);
    }

    @Override
    public SiameseResult signIn(SignInBo signInBo) {
        return userServiceProxy.signIn(signInBo);
    }
}
