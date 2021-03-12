package com.haiyan.deflower.controller;

import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.User;

/**
 * @author haiyan
 */
@Api(tags = "收货地址")
@RestController
@RequestMapping("/background/user")
public class BackgroundUserController {
    private final BackgroundUserService backgroundUserService;

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

    /**
     * 加载后台用户
     * @param pageDomain 参数
     * @return 结果
     */
    public PageList<BackgroundUser> ListUser(PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success(userService.updateUser(user));
        return ajaxResult;
    }
}
