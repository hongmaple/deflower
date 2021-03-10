package com.haiyan.deflower.controller;

import com.haiyan.deflower.pojo.AjaxResult;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author haiyan
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public AjaxResult register(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.register(user));
        return ajaxResult;
    }

    /**
     * 登陆
     * @param user 参数
     * @return 结果
     */
    @PostMapping("/login")
    @ApiOperation("登陆")
    public AjaxResult login(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.login(user));
        return ajaxResult;
    }

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    @PutMapping
    @ApiOperation("修改用户信息")
    public AjaxResult updateUser(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.updateUser(user));
        return ajaxResult;
    }
}
