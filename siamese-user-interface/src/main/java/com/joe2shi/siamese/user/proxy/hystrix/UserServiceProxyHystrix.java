package com.joe2shi.siamese.user.proxy.hystrix;

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
    public SiameseResult validation(CheckBo checkBo) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult register(RegisterBo registerBo) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult accredit(AccreditBo accreditBo) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult user() {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
