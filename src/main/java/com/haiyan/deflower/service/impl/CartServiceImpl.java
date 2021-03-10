package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.CartDao;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.mapper.CartMapper;
import com.haiyan.deflower.pojo.Cart;
import com.haiyan.deflower.pojo.Flower;
import com.haiyan.deflower.pojo.PageDomain;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiyan
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartDao cartDao, CartMapper cartMapper) {
        this.cartDao = cartDao;
        this.cartMapper = cartMapper;
    }

    @Override
    public Long addCart(Cart cart) {
        if(cartDao.save(cart)) {
            throw new ExceptionResult("cart","false",null,"添加失败");
        }
        return cart.getId();
    }

    @Override
    public Boolean updateCart(Cart cart) {
        if (!cartDao.lambdaUpdate().update(cart)) {
            throw new ExceptionResult("cart","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public Boolean deletedCart(Long id) {
        if(cartMapper.deleteById(id)==0) {
            throw new ExceptionResult("cart","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public PageList<Cart> listCart(PageDomain pageDomain) {
        Page<Cart> page = cartDao.lambdaQuery().page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        List<Cart> carts = page.getRecords();
        return PageList.of(carts, page);
    }
}
