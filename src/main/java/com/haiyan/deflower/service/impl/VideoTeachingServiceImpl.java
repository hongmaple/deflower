package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.VideoTeachingDao;
import com.haiyan.deflower.pojo.PageDomain;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.VideoTeaching;
import com.haiyan.deflower.service.VideoTeachingService;
import org.springframework.stereotype.Service;

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
    public PageList<VideoTeaching> videoTeachingList(PageDomain pageDomain) {
        Page<VideoTeaching> page = videoTeachingDao.lambdaQuery().page(new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize()));
        return PageList.of(page.getRecords(),page);
    }

    @Override
    public Boolean deletedVideoTeaching(Long id) {
        return videoTeachingDao.removeById(id);
    }
}
