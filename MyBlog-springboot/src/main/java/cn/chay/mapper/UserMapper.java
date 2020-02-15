package cn.chay.mapper;

import cn.chay.base.mapper.BaseMapper;
import cn.chay.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sang on 17-3-10.
 */
public interface UserMapper extends BaseMapper<User> {
    User getUser(@Param("username") String username, @Param("password") String password);
}
