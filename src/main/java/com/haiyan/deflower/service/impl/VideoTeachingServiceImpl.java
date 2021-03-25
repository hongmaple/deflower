package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.VideoTeachingDao;
import com.haiyan.deflower.dto.request.VideoTeachingQuery;
import com.haiyan.deflower.pojo.PageDomain;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.VideoTeaching;
import com.haiyan.deflower.service.VideoTeachingService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author haiyan
 */
@Service
public class VideoTeachingServiceImpl implements VideoTeachingService {
    private final VideoTeachingDao videoTeachingDao;

    public VideoTeachingServiceImpl(VideoTeachingDao videoTeachingDao) {
        this.videoTeachingDao = videoTeachingDao;
    }

    @Override
    public Long addVideoTeaching(VideoTeaching videoTeaching) {
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
        Page<VideoTeaching> page = lambdaQuery.page(new Page<>(query.getPageNum(), query.getPageSize()));
        return PageList.of(page.getRecords(),page);
    }

    @Override
    public Boolean deletedVideoTeaching(Long id) {
        return videoTeachingDao.removeById(id);
    }
}
