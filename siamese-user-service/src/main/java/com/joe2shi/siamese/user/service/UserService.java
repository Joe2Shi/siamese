package com.joe2shi.siamese.user.service;

import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
import com.joe2shi.siamese.common.vo.SiameseResult;

@SuppressWarnings("rawtypes")
public interface UserService {
    /**
     * 验证用户信息是否唯一
     *
     * @param checkBo
     * @return
     */
    SiameseResult validation(CheckBo checkBo);

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
     * @param accreditBo
     * @return
     */
    SiameseResult accredit(AccreditBo accreditBo);

    /**
     * 通过 id 获取用户信息
     *
     * @param id
     * @return
     */
    SiameseResult user(String id);
}
