package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.FlowerDao;
import com.haiyan.deflower.dto.request.FlowerQuery;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.pojo.Flower;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.service.FlowerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author haiyan
 */
@Service
public class FlowerServiceImpl implements FlowerService {

    private final FlowerDao flowerDao;

    public FlowerServiceImpl(FlowerDao flowerDao) {
        this.flowerDao = flowerDao;
    }

    @Override
    public Long addFlower(Flower flower) {
        flower.setCreateTime(new Date());
        flower.setUpdateTime(new Date());
        if(!flowerDao.save(flower)) {
            throw new ExceptionResult("flower","false",null,"新增花朵失败");
        }
        return flower.getId();
    }

    @Override
    public Boolean updateFlower(Flower flower) {
        flower.setUpdateTime(new Date());
        if(!flowerDao.lambdaUpdate().update(flower)) {
            throw new ExceptionResult("flower","false",null,"修改花朵失败");
        }
        return true;
    }

    @Override
    public PageList<Flower> listFlower(FlowerQuery query) {
        LambdaQueryChainWrapper<Flower> lambdaQuery = flowerDao.lambdaQuery();
        if (query.getCid()!=null) {
            lambdaQuery.eq(Flower::getCid,query.getCid());
        }

        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(Flower::getTitle,query.getTitle());
        }
        Page<Flower> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<Flower> flowers = page.getRecords();
        return PageList.of(flowers, page);
    }
}
