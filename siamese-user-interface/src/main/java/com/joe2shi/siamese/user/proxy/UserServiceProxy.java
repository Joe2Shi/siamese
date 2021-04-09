package com.joe2shi.siamese.user.proxy;

import com.joe2shi.siamese.user.dto.CheckDto;
import com.joe2shi.siamese.user.dto.RegisterDto;
import com.joe2shi.siamese.user.dto.AccreditDto;
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
    SiameseResult validation(@RequestBody CheckDto check);

    @PostMapping("register")
    SiameseResult register(@RequestBody RegisterDto register);

    @PostMapping("accredit")
    SiameseResult accredit(@RequestBody AccreditDto accredit);

    @GetMapping("user")
    SiameseResult user();
}
