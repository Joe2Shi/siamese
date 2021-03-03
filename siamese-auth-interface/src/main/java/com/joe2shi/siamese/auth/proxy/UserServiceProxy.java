package com.joe2shi.siamese.auth.proxy;

import com.joe2shi.siamese.auth.bo.RegisterBo;
import com.joe2shi.siamese.auth.bo.SignInBo;
import com.joe2shi.siamese.auth.proxy.hystrix.UserServiceProxyHystrix;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="siamese-auth-service", fallback = UserServiceProxyHystrix.class, path = "/auth")
@SuppressWarnings("rawtypes")
public interface UserServiceProxy {
    @PostMapping("register")
    SiameseResult register(@RequestBody RegisterBo registerBo);

    @PostMapping("signin")
    SiameseResult signIn(@RequestBody SignInBo signInBo);
}
