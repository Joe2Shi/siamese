package com.joe2shi.siamese.auth.service;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.common.vo.SiameseResult;

@SuppressWarnings("rawtypes")
public interface UserService {
    /**
     * 用户注册
     *
     * @param registerBo
     * @return
     */
    SiameseResult register(RegisterBo registerBo);

    /**
     * 用户登录
     *
     * @param signInBo
     * @return
     */
    SiameseResult signIn(SignInBo signInBo);
}
