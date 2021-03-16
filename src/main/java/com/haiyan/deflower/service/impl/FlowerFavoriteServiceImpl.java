package com.haiyan.deflower.service.impl;

import com.haiyan.deflower.dao.FlowerFavoriteDao;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.mapper.FlowerFavoriteMapper;
import com.haiyan.deflower.pojo.FlowerFavorite;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.service.FlowerFavoriteService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class FlowerFavoriteServiceImpl implements FlowerFavoriteService {

    private final FlowerFavoriteDao flowerFavoriteDao;
    private final FlowerFavoriteMapper flowerFavoriteMapper;

    @Autowired
    private UserUtils userUtils;

    public FlowerFavoriteServiceImpl(FlowerFavoriteDao flowerFavoriteDao, FlowerFavoriteMapper flowerFavoriteMapper) {
        this.flowerFavoriteDao = flowerFavoriteDao;
        this.flowerFavoriteMapper = flowerFavoriteMapper;
    }

    @Override
    public Long addFlowerFavorite(FlowerFavorite flowerFavorite) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        flowerFavorite.setUid(user.getId());
        if (!flowerFavoriteDao.save(flowerFavorite)) {
            throw new ExceptionResult("flowerFavorite","false",null,"收藏失败");
        }
        return flowerFavorite.getId();
    }

    @Override
    public Boolean deletedFlowerFavorite(Long id) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (flowerFavoriteMapper.deleteById(id)==0) {
            throw new ExceptionResult("flowerFavorite","false",null,"取消收藏失败");
        }
        return true;
    }

    @Override
    public Boolean verify(Long favoriteId) {
        if(flowerFavoriteDao.lambdaQuery().eq(FlowerFavorite::getFavoriteId,favoriteId).count()==0) {
            return false;
        }
        return true;
    }
}
