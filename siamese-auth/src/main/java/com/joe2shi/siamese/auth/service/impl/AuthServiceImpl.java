package com.joe2shi.siamese.auth.service.impl;

import com.joe2shi.siamese.auth.dto.AccreditDto;
import com.joe2shi.siamese.auth.config.JwtProperties;
import com.joe2shi.siamese.auth.service.AuthService;
import com.joe2shi.siamese.auth.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.utils.JwtUtils;
import com.joe2shi.siamese.common.utils.UserInfo;
import com.joe2shi.siamese.common.vo.SiameseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
@SuppressWarnings("rawtypes")
public class AuthServiceImpl implements AuthService {
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private UserServiceProxy userServiceProxy;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override

    public SiameseResult accredit(AccreditDto accredit) {
        try {
            // Auth user
            SiameseResult result = userServiceProxy.accredit(accredit);
            if (result.getCode() != SystemConstant.SUCCESS_CODE) {
                return result;
            }
            // Extend the token time if you have already sign in
            UserInfo userInfo = new UserInfo((String) result.getData(), System.currentTimeMillis());
            String redisToken = redisTemplate.boundValueOps(userInfo.getId()).get();
            if (!StringUtils.isBlank(redisToken)) {
                redisTemplate.expire(userInfo.getId(), SystemConstant.NUMBER_THIRTY, TimeUnit.MINUTES);
                return new SiameseResult<>(ResponseEnum.SIGN_IN_SUCCESS, redisToken);
            } else {
                // Generate token
                String token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey());
                // Save in redis, duration is 30 minutes
                redisTemplate.boundValueOps(userInfo.getId()).set(token, SystemConstant.NUMBER_THIRTY, TimeUnit.MINUTES);
                return new SiameseResult<>(ResponseEnum.SIGN_IN_SUCCESS, token);
            }
        } catch (Exception e) {
            log.error(ResponseEnum.GENERATE_TOKEN_FAILED.getMessage() + SystemConstant.CHARACTER_COLON + SystemConstant.CHARACTER_SPACE + e.getMessage());
            throw new SiameseException(ResponseEnum.GENERATE_TOKEN_FAILED);
        }
    }
}
