package com.joe2shi.siamese.user.proxy;

import com.joe2shi.siamese.user.bo.CheckBo;
import com.joe2shi.siamese.user.bo.RegisterBo;
import com.joe2shi.siamese.user.bo.AccreditBo;
import com.joe2shi.siamese.user.proxy.hystrix.UserServiceProxyHystrix;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "siamese-user-service", fallback = UserServiceProxyHystrix.class)
@SuppressWarnings("rawtypes")
public interface UserServiceProxy {
    @PostMapping("validation")
    SiameseResult validation(@RequestBody CheckBo check);

    @PostMapping("register")
    SiameseResult register(@RequestBody RegisterBo register);

    @PostMapping("accredit")
    SiameseResult accredit(@RequestBody AccreditBo accredit);

    @GetMapping("user")
    SiameseResult user();
}
