package com.joe2shi.siamese.user.mapper;

import com.joe2shi.siamese.user.base.BaseMapper;
import com.joe2shi.siamese.user.entity.SiameseUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SiameseUserEntity> {
}
