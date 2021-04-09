package com.joe2shi.siamese.user.proxy.hystrix;

import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.user.dto.CheckDto;
import com.joe2shi.siamese.user.dto.RegisterDto;
import com.joe2shi.siamese.user.dto.AccreditDto;
import com.joe2shi.siamese.user.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class UserServiceProxyHystrix implements UserServiceProxy {
    @Override
    public SiameseResult validation(CheckDto check) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult register(RegisterDto register) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult accredit(AccreditDto accredit) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult user() {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
