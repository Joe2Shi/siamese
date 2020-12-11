package com.joe2shi.siamses.common.base;

import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T>, DeleteByIdListMapper<T, String> {
}
