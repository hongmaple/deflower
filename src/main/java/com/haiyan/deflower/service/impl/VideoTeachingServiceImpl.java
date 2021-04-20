package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.VideoTeachingDao;
import com.haiyan.deflower.dto.request.VideoTeachingQuery;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.User;
import com.haiyan.deflower.pojo.VideoTeaching;
import com.haiyan.deflower.service.VideoTeachingService;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author haiyan
 */
@Service
public class VideoTeachingServiceImpl implements VideoTeachingService {
    private final VideoTeachingDao videoTeachingDao;
    private final UserUtils userUtils;

    public VideoTeachingServiceImpl(VideoTeachingDao videoTeachingDao, UserUtils userUtils) {
        this.videoTeachingDao = videoTeachingDao;
        this.userUtils = userUtils;
    }

    @Override
    public Long addVideoTeaching(VideoTeaching videoTeaching) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        videoTeaching.setCreatorId(user.getId());
        videoTeachingDao.save(videoTeaching);
        return videoTeaching.getId();
    }

    @Override
    public Boolean updateVideoTeaching(VideoTeaching videoTeaching) {
        return videoTeachingDao.updateById(videoTeaching);
    }

    @Override
    public PageList<VideoTeaching> videoTeachingList(VideoTeachingQuery query) {
        LambdaQueryChainWrapper<VideoTeaching> lambdaQuery = videoTeachingDao.lambdaQuery();
        if (StringUtils.hasText(query.getTitle())) {
            lambdaQuery.like(VideoTeaching::getTitle,query.getTitle());
        }
        if (query.getCreatorId()!=null) {
            lambdaQuery.eq(VideoTeaching::getCreatorId,query.getCreatorId());
        }
        if (query.getCreatorType()!=null) {
            lambdaQuery.eq(VideoTeaching::getCreatorType,query.getCreatorType());
        }
        Page<VideoTeaching> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        return PageList.of(page.getRecords(),page);
    }

    @Override
    public Boolean deletedVideoTeaching(Long id) {
        return videoTeachingDao.removeById(id);
    }

    @Override
    public VideoTeaching getVideoTeachingById(Long id) {
        return videoTeachingDao.getById(id);
    }
}
