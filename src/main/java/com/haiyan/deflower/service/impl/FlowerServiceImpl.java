package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.CategoryDao;
import com.haiyan.deflower.dao.FlowerDao;
import com.haiyan.deflower.dto.request.FlowerQuery;
import com.haiyan.deflower.dto.response.FlowerRowVo;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.pojo.Category;
import com.haiyan.deflower.pojo.Flower;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.service.FlowerService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class FlowerServiceImpl implements FlowerService {

    private final FlowerDao flowerDao;
    private final CategoryDao categoryDao;
    private final ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    public FlowerServiceImpl(FlowerDao flowerDao, CategoryDao categoryDao, ModelMapper modelMapper) {
        this.flowerDao = flowerDao;
        this.categoryDao = categoryDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addFlower(Flower flower) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        flower.setCreateTime(new Date());
        flower.setUpdateTime(new Date());
        if(!flowerDao.save(flower)) {
            throw new ExceptionResult("flower","false",null,"新增花朵失败");
        }
        return flower.getId();
    }

    @Override
    public Boolean updateFlower(Flower flower) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        flower.setUpdateTime(new Date());
        if(!flowerDao.lambdaUpdate().eq(Flower::getId,flower.getId()).update(flower)) {
            throw new ExceptionResult("flower","false",null,"修改花朵失败");
        }
        return true;
    }

    @Override
    public PageList<FlowerRowVo> listFlower(FlowerQuery query) {
        LambdaQueryChainWrapper<Flower> lambdaQuery = flowerDao.lambdaQuery();
        if (query.getCid()!=null) {
            lambdaQuery.eq(Flower::getCid,query.getCid());
        }

        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(Flower::getTitle,query.getTitle());
        }
        lambdaQuery.orderByDesc(Flower::getUpdateTime);
        Page<Flower> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<Flower> flowers = page.getRecords();
        List<FlowerRowVo> flowerRowVos = new ArrayList<>();
        flowers.forEach(flower -> {
            Category category = categoryDao.getById(flower.getCid());
            FlowerRowVo flowerRowVo = modelMapper.map(flower,FlowerRowVo.class);
            flowerRowVo.setCategoryName(Objects.isNull(category)? "分类已删除":category.getName());
            flowerRowVos.add(flowerRowVo);
        });
        return PageList.of(flowerRowVos, page);
    }
}
