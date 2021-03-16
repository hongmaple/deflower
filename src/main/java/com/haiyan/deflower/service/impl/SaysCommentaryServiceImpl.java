package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.SaysCommentaryDao;
import com.haiyan.deflower.dto.request.SaysCommentaryQuery;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.mapper.SaysCommentaryMapper;
import com.haiyan.deflower.pojo.*;
import com.haiyan.deflower.service.SaysCommentaryService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class SaysCommentaryServiceImpl implements SaysCommentaryService {

    private final SaysCommentaryDao commentaryDao;
    private final SaysCommentaryMapper commentaryMapper;

    @Autowired
    private UserUtils userUtils;

    public SaysCommentaryServiceImpl(SaysCommentaryDao commentaryDao, SaysCommentaryMapper commentaryMapper) {
        this.commentaryDao = commentaryDao;
        this.commentaryMapper = commentaryMapper;
    }

    @Override
    public Long addSaysCommentary(SaysCommentary saysCommentary) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if(!commentaryDao.save(saysCommentary)) {
            throw new ExceptionResult("saysCommentary","false",null,"添加失败");
        }
        return saysCommentary.getId();
    }

    @Override
    public Boolean updateSaysCommentary(SaysCommentary saysCommentary) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (!commentaryDao.updateById(saysCommentary)) {
            throw new ExceptionResult("SaysCommentary","false",null,"修改失败");
        }
        return true;
    }

    @Override
    public Boolean deletedSaysCommentary(Long id) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        if (commentaryMapper.deleteById(id)==0) {
            throw new ExceptionResult("SaysCommentary","false",null,"删除失败");
        }
        return true;
    }

    @Override
    public PageList<SaysCommentary> listSaysCommentary(SaysCommentaryQuery query) {
        LambdaQueryChainWrapper<SaysCommentary> lambdaQuery = commentaryDao.lambdaQuery();
        lambdaQuery.orderByDesc(SaysCommentary::getId);
        Page<SaysCommentary> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<SaysCommentary> saysCommentaries = page.getRecords();
        return PageList.of(saysCommentaries, page);
    }
}
