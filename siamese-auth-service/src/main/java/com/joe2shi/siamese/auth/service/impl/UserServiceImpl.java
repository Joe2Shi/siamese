package com.joe2shi.siamese.auth.service.impl;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.auth.entity.SiameseUserEntity;
import com.joe2shi.siamese.auth.mapper.UserMapper;
import com.joe2shi.siamese.auth.service.UserService;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.constant.RegularConstant;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public SiameseResult register(RegisterBo registerBo) {
        String username = registerBo.getUsername();
        String password = registerBo.getPassword();
        // Check user information
        if (!username.matches(RegularConstant.checkUsername)) {
            throw new SiameseException(ResponseEnum.INVALID_USERNAME);
        }
        if (!password.matches(RegularConstant.checkPassword)) {
            throw new SiameseException(ResponseEnum.INVALID_PASSWORD);
        }
        try {
            // Insert user information
            String id = UUID.randomUUID().toString().replaceAll(SystemConstant.STRING_HYPHEN, SystemConstant.STRING_NULL);
            SiameseUserEntity siameseUserEntity = new SiameseUserEntity();
            siameseUserEntity.setId(id);
            siameseUserEntity.setUsername(username);
            siameseUserEntity.setPassword(password);
            siameseUserEntity.setCreateTime(System.currentTimeMillis());
            int result = userMapper.insert(siameseUserEntity);
            if (result != SystemConstant.NUMBER_ONE)
                throw new SiameseException(ResponseEnum.REGISTER_FAILED);
            else
                return new SiameseResult(ResponseEnum.REGISTER_SUCCESS);
        } catch (Exception e) {
            log.error(LoggerConstant.REGISTER_FAILED + e.getMessage());
            throw new SiameseException(ResponseEnum.REGISTER_FAILED);
        }
    }

    @Override
    public SiameseResult signIn(SignInBo signInBo) {
        String username = signInBo.getUsername();
        String password = signInBo.getPassword();
        Example example = new Example(SiameseUserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.orEqualTo("phoneNumber", username);
        SiameseUserEntity item = userMapper.selectOneByExample(example);
        if (item.getPassword().equals(password))
            return new SiameseResult<>(ResponseEnum.SIGN_IN_SUCCESS, item);
        else
            throw new SiameseException(ResponseEnum.WRONG_USERNAME_OR_PASSWORD);
    }
}
