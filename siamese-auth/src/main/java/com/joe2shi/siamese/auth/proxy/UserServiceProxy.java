package com.joe2shi.siamese.auth.proxy;

import com.joe2shi.siamese.auth.dto.AccreditDto;
import com.joe2shi.siamese.auth.proxy.fallback.UserServiceProxyHystrix;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "siamese-user-interface", fallback = UserServiceProxyHystrix.class)
@SuppressWarnings("rawtypes")
public interface UserServiceProxy {
    @PostMapping("accredit")
    SiameseResult accredit(@RequestBody AccreditDto accredit);
}
