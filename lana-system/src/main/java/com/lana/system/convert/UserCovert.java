package com.lana.system.convert;

import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.SysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserCovert {
    UserCovert INSTANCE = Mappers.getMapper(UserCovert.class);


    UserDetail convertDetail(SysUserEntity entity);


}
