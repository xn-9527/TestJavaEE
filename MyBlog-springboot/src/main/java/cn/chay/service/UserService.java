package cn.chay.service;

import cn.chay.mapper.UserMapper;
import cn.chay.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sang on 17-3-10.
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public boolean login(String username, String password) {
        User user = userMapper.getUser(username, password);
        if (user == null) {
            return false;
        }else{
            return true;
        }
    }
}
