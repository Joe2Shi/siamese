package com.joe2shi.siamese.item.proxy;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.proxy.impl.ArticleServiceProxyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "siamese-user-service", fallback = ArticleServiceProxyHystrix.class)
@SuppressWarnings("rawtypes")
public interface UserServiceProxy {
    @GetMapping("tx-manage")
    SiameseResult txManage();
}
