package com.sec.oauth2.mapper;

import com.sec.oauth2.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/4.
 * @description
 */
@Mapper
public interface UserMapper {

    User getUserByUsername(@Param("username") String username);

    List<String> getUserAuthority(@Param("username") String username);

}
