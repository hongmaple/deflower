package com.haiyan.deflower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haiyan
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    @ApiOperation("注册")
    public void register() {

    }
}
