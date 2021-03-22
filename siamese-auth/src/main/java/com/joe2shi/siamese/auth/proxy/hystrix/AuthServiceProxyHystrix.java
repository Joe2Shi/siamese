package com.joe2shi.siamese.auth.proxy.hystrix;

import com.joe2shi.siamese.auth.bo.AccreditBo;
import com.joe2shi.siamese.auth.proxy.AuthServiceProxy;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class AuthServiceProxyHystrix implements AuthServiceProxy {
    @Override
    public SiameseResult accredit(AccreditBo accredit) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
