package com.joe2shi.siamese.auth.proxy.fallback;

import com.joe2shi.siamese.auth.dto.AccreditDto;
import com.joe2shi.siamese.auth.proxy.UserServiceProxy;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class UserServiceProxyHystrix implements UserServiceProxy {
    @Override
    public SiameseResult accredit(AccreditDto accredit) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
