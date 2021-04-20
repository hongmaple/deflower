package com.haiyan.deflower.service;

import com.haiyan.deflower.dto.request.VideoTeachingQuery;
import com.haiyan.deflower.pojo.PageDomain;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.VideoTeaching;

/**
 * @author haiyan
 */
public interface VideoTeachingService {
    /**
     * 添加视频教学
     * @param videoTeaching 视频
     * @return 视频id
     */
    Long addVideoTeaching(VideoTeaching videoTeaching);

    /**
     * 修改视频
     * @param videoTeaching 视频
     * @return 结果
     */
    Boolean updateVideoTeaching(VideoTeaching videoTeaching);

    /**
     * 加载视频
     * @param query 参数
     * @return 视频
     */
    PageList<VideoTeaching> videoTeachingList(VideoTeachingQuery query);

    /**
     *
     * @param id 视频id
     * @return 结果
     */
    Boolean deletedVideoTeaching(Long id);

    /**
     * 根据ID查询视频详情
     * @param id id
     * @return 详情
     */
    VideoTeaching getVideoTeachingById(Long id);
}
