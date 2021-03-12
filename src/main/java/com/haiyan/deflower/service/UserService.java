package com.haiyan.deflower.service;

import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.User;

/**
 * @author haiyan
 */
public interface UserService {
    /**
     * 注册
     * @param user 参数
     * @return 结果
     */
    Boolean register(User user);

    /**
     * 登陆
     * @param user 参数
     * @return 结果
     */
    Boolean login(User user);

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    Boolean updateUser(User user);
}
