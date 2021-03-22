package com.joe2shi.siamese.auth.service;

import com.joe2shi.siamese.auth.bo.AccreditBo;
import com.joe2shi.siamese.common.vo.SiameseResult;

@SuppressWarnings("rawtypes")
public interface AuthService {
    /**
     * Generate token
     *
     * @param accredit
     * @return
     */
    SiameseResult accredit(AccreditBo accredit);
}
