package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.FlowerDao;
import com.haiyan.deflower.dao.FlowerFavoriteDao;
import com.haiyan.deflower.dao.VideoTeachingDao;
import com.haiyan.deflower.dto.request.CollectionQuery;
import com.haiyan.deflower.dto.response.FlowerRowVo;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.pojo.*;
import com.haiyan.deflower.service.FlowerFavoriteService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class FlowerFavoriteServiceImpl implements FlowerFavoriteService {

    private final FlowerFavoriteDao flowerFavoriteDao;
    private final VideoTeachingDao videoTeachingDao;
    private final FlowerDao flowerDao;
    private final ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    public FlowerFavoriteServiceImpl(FlowerFavoriteDao flowerFavoriteDao, VideoTeachingDao videoTeachingDao, FlowerDao flowerDao, ModelMapper modelMapper) {
        this.flowerFavoriteDao = flowerFavoriteDao;
        this.videoTeachingDao = videoTeachingDao;
        this.flowerDao = flowerDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addFlowerFavorite(FlowerFavorite flowerFavorite) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        flowerFavorite.setCreateTime(new Date());
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
        if (!flowerFavoriteDao.lambdaUpdate().eq(FlowerFavorite::getFavoriteId,id).remove()) {
            throw new ExceptionResult("flowerFavorite","false",null,"取消收藏失败");
        }
        return true;
    }

    @Override
    public Boolean verify(Long favoriteId,Integer type) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if(flowerFavoriteDao.lambdaQuery().eq(FlowerFavorite::getType,type).eq(FlowerFavorite::getUid,user.getId()).eq(FlowerFavorite::getFavoriteId,favoriteId).count()==0) {
            return false;
        }
        return true;
    }

    @Override
    public PageList<?> listCollection(CollectionQuery collectionQuery) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Page<FlowerFavorite> page = flowerFavoriteDao
                .lambdaQuery()
                .eq(FlowerFavorite::getUid, user.getId())
                .eq(FlowerFavorite::getType,collectionQuery.getType())
                .orderByDesc(FlowerFavorite::getCreateTime)
                .page(new Page<>(collectionQuery.getPageNum(), collectionQuery.getPageSize()));
        if (collectionQuery.getType()==0) {
            return getFlowerCollection(page);
        }
        return getVideoCollection(page);
    }

    private PageList<?> getFlowerCollection(Page<FlowerFavorite> page) {
        List<FlowerRowVo> flowerRowVos = new ArrayList<>();
        page.getRecords().forEach(flowerFavorite -> {
            Flower flower = flowerDao.getById(flowerFavorite.getFavoriteId());
            if (!Objects.isNull(flower)) {
                FlowerRowVo flowerRowVo = modelMapper.map(flower,FlowerRowVo.class);
                flowerRowVo.setCategoryName("");
                flowerRowVos.add(flowerRowVo);
            }
        });
        return PageList.of(flowerRowVos, page);
    }

    private PageList<?> getVideoCollection(Page<FlowerFavorite> page) {
        List<VideoTeaching> videoTeachings = new ArrayList<>();
        page.getRecords().forEach(flowerFavorite -> {
            VideoTeaching videoTeaching = videoTeachingDao.getById(flowerFavorite.getFavoriteId());
            if (!Objects.isNull(videoTeaching)) {
                VideoTeaching flowerRowVo = modelMapper.map(videoTeaching,VideoTeaching.class);
                videoTeachings.add(flowerRowVo);
            }
        });
        return PageList.of(videoTeachings, page);
    }

    @Override
    public Integer getFlowerFavoriteCunt() {
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        return flowerFavoriteDao.lambdaQuery().eq(FlowerFavorite::getUid,user.getId()).count();
    }
}
