package com.joe2shi.siamese.auth.service.impl;

import com.joe2shi.siamese.auth.bo.AccreditBo;
import com.joe2shi.siamese.auth.config.JwtProperties;
import com.joe2shi.siamese.auth.service.AuthService;
import com.joe2shi.siamese.auth.proxy.AuthServiceProxy;
import com.joe2shi.siamese.auth.utils.JwtUtils;
import com.joe2shi.siamese.auth.utils.UserInfo;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
@SuppressWarnings("rawtypes")
public class AuthServiceImpl implements AuthService {
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private AuthServiceProxy authServiceProxy;

    @Override
    public SiameseResult accredit(AccreditBo accreditBo) {
        try {
            // Auth user
            SiameseResult result = authServiceProxy.accredit(accreditBo);
            if (result.getCode() != SystemConstant.SUCCESS_CODE) {
                return result;
            }
            // Generate token
            UserInfo userInfo = new UserInfo((String) result.getData());
            String token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            // Save in redis, duration is 30 minutes
            
            return new SiameseResult<>(ResponseEnum.SIGN_IN_SUCCESS, token);
        } catch (Exception e) {
            log.error(LoggerConstant.GENERATE_TOKEN_FAILED + e.getMessage());
            throw new SiameseException(ResponseEnum.GENERATE_TOKEN_FAILED);
        }
    }
}
