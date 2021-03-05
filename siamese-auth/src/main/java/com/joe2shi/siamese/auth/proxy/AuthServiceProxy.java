package com.joe2shi.siamese.auth.proxy;

import com.joe2shi.siamese.auth.bo.AccreditBo;
import com.joe2shi.siamese.auth.proxy.hystrix.AuthServiceProxyHystrix;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "siamese-user-interface", fallback = AuthServiceProxyHystrix.class)
@SuppressWarnings("rawtypes")
public interface AuthServiceProxy {
    @PostMapping("accredit")
    SiameseResult accredit(@RequestBody AccreditBo accreditBo);
}
