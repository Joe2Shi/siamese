package com.joe2shi.siamese.txmanage.proxy.impl;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.txmanage.proxy.UserServiceProxy;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class UserServiceProxyHystrix implements UserServiceProxy {

    @Override
    public SiameseResult txManage() {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
