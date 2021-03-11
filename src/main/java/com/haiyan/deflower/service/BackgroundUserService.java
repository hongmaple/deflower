package com.haiyan.deflower.service;

import com.haiyan.deflower.pojo.BackgroundUser;
/**
 * @author haiyan
 */
public interface BackgroundUserService {
    /**
     * 添加用户
     * @param user 参数
     * @return 结果
     */
    Boolean register(BackgroundUser user);

    /**
     * 登陆
     * @param user 参数
     * @return 结果
     */
    Boolean login(BackgroundUser user);

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    Boolean updateUser(BackgroundUser user);
}
