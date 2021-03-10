package com.haiyan.deflower.service.impl;

import com.haiyan.deflower.dao.CategoryDao;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.pojo.Category;
import com.haiyan.deflower.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiyan
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Long addCategory(Category category) {
        if (categoryDao.lambdaQuery().eq(Category::getName,category.getName()).count()>0) {
            throw new ExceptionResult("category","false",null,"新增分类失败，该分类已存在");
        }
        if(!categoryDao.save(category)) {
            throw new ExceptionResult("category","false",null,"新增分类失败");
        }
        return category.getId();
    }

    @Override
    public Boolean updateCategory(Category category) {
        if (categoryDao.getById(category.getId()).getName().equals(category.getName())) {
            throw new ExceptionResult("category","false",null,"内容未修改");
        }
        if (categoryDao.lambdaQuery().eq(Category::getName,category.getName()).ne(Category::getId,category.getId()).count()>0) {
            throw new ExceptionResult("category","false",null,"新增分类失败，该分类已存在");
        }
        if(!categoryDao.lambdaUpdate().update(category)) {
            throw new ExceptionResult("category","false",null,"新增分类失败");
        }
        return true;
    }

    @Override
    public List<Category> queryCategory() {
        return categoryDao.lambdaQuery().list();
    }
}
