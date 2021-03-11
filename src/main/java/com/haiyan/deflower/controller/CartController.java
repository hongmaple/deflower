package com.haiyan.deflower.controller;

import com.haiyan.deflower.pojo.AjaxResult;
import com.haiyan.deflower.pojo.Cart;
import com.haiyan.deflower.pojo.PageDomain;
import com.haiyan.deflower.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车
 *
 * @author haiyan
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 添加购物车
     * @param cart 参数
     * @return id
     */
    @ApiOperation("添加购物车")
    @PostMapping()
    public AjaxResult addCart(@RequestBody Cart cart) {
        AjaxResult ajaxResult = AjaxResult.success("添加购物车成功",cartService.addCart(cart));
        return ajaxResult;
    }

    /**
     * 修改购物车中的数据
     * @param cart 参数
     * @return 结果
     */
    @ApiOperation("修改购物车中的数据")
    @PutMapping()
    public AjaxResult updateCart(@RequestBody Cart cart) {
        AjaxResult ajaxResult = AjaxResult.success("修改购物车成功",cartService.updateCart(cart));
        return ajaxResult;
    }

    /**
     * 删除购物车数据
     * @param id 购物车id
     * @return 结果
     */
    @ApiOperation("删除购物车数据")
    @DeleteMapping("/{id}")
    public AjaxResult deletedCart(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success("修改购物车成功",cartService.deletedCart(id));
        return ajaxResult;
    }

    /**
     * 分页查询购物车
     * @param pageDomain 分页数据
     * @return 购物车数据
     */
    @ApiOperation("分页查询购物车")
    @PostMapping("/list")
    public AjaxResult listCart(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success("修改购物车成功",cartService.listCart(pageDomain));
        return ajaxResult;
    }

}
