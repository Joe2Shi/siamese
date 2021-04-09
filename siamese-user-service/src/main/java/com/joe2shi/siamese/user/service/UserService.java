package com.joe2shi.siamese.user.service;

import com.joe2shi.siamese.user.dto.CheckDto;
import com.joe2shi.siamese.user.dto.RegisterDto;
import com.joe2shi.siamese.user.dto.AccreditDto;
import com.joe2shi.siamese.common.vo.SiameseResult;

@SuppressWarnings("rawtypes")
public interface UserService {
    /**
     * 验证用户信息是否唯一
     *
     * @param check
     * @return
     */
    SiameseResult validation(CheckDto check);

    /**
     * 用户注册
     *
     * @param register
     * @return
     */
    SiameseResult register(RegisterDto register);

    /**
     * 用户登录
     *
     * @param accredit
     * @return
     */
    SiameseResult accredit(AccreditDto accredit);

    /**
     * 通过 id 获取用户信息
     *
     * @param id
     * @return
     */
    SiameseResult user(String id);
}
