package com.joe2shi.siamese.auth.proxy.hystrix;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.auth.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class UserServiceProxyHystrix implements UserServiceProxy {
    @Override
    public SiameseResult register(RegisterBo registerBo) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult signIn(SignInBo signInBo) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
