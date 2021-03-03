package com.joe2shi.siamese.auth.mapper;

import com.joe2shi.siamese.auth.base.BaseMapper;
import com.joe2shi.siamese.auth.entity.SiameseUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SiameseUserEntity> {
}
