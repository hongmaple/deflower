package com.haiyan.deflower.service.impl;

import com.haiyan.deflower.dao.BackgroundUserDao;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.mapper.BackgroundUserMapper;
import com.haiyan.deflower.pojo.BackgroundUser;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.service.BackgroundUserService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class BackgroundUserServiceImpl implements BackgroundUserService {

    private final BackgroundUserMapper userMapper;
    private final BackgroundUserDao userDao;

    @Autowired
    private UserUtils userUtils;

    public BackgroundUserServiceImpl(BackgroundUserMapper userMapper, BackgroundUserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @Override
    public Boolean register(BackgroundUser user) {
        if(userDao.lambdaQuery().eq(BackgroundUser::getPhone,user.getPhone()).count()>0) {
            throw new ExceptionResult("user","false",null,"注册失败，手机号码已存在");
        }
        user.setCreateTime(new Date());
        if (userMapper.insert(user)==0) {
            throw new ExceptionResult("user","false",null,"注册失败");
        }
        return true;
    }

    @Override
    public Boolean login(BackgroundUser user) {
        if (userDao.lambdaQuery()
                .eq(BackgroundUser::getPhone,user.getPhone())
                .count()==0) {
            throw new ExceptionResult("user","false",null,"登陆失败，手机号码不存在");
        }
        BackgroundUser loginUser = userDao.lambdaQuery()
                .eq(BackgroundUser::getPhone, user.getPhone())
                .eq(BackgroundUser::getPassword, user.getPassword())
                .one();
        if(Objects.isNull(loginUser)) {
            throw new ExceptionResult("user","false",null,"登陆失败，密码不正确");
        }
        HttpSession session = ServletUtils.getSession();
        session.setAttribute("token", loginUser);
        //session过期时间设置，以秒为单位，即在没有活动120分钟后，session将失效
        session.setMaxInactiveInterval(120 * 60);
        return true;
    }

    @Override
    public Boolean updateUser(BackgroundUser user) {
        User loginUser = userUtils.getUser(ServletUtils.getRequest());
        if (userDao.lambdaQuery().eq(BackgroundUser::getId,loginUser.getId()).count()==0) {
            throw new ExceptionResult("user","false",null,"该用户不存在");
        }
        if(!userDao.lambdaUpdate().update(user)) {
            throw new ExceptionResult("user","false",null,"修改失败");
        }
        return true;
    }
}
