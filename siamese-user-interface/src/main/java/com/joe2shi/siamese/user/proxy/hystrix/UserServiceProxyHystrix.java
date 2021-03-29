package com.joe2shi.siamese.user.proxy.hystrix;

import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
import com.joe2shi.siamese.user.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class UserServiceProxyHystrix implements UserServiceProxy {
    @Override
    public SiameseResult validation(CheckBo check) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult register(RegisterBo register) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult accredit(AccreditBo accredit) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult user() {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
