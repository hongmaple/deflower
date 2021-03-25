package com.haiyan.deflower.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haiyan.deflower.dao.VideoTeachingDao;
import com.haiyan.deflower.mapper.VideoTeachingMapper;
import com.haiyan.deflower.pojo.VideoTeaching;
import org.springframework.stereotype.Repository;

/**
 * @author haiyan
 */
@Repository
public class VideoTeachingDaoImpl extends ServiceImpl<VideoTeachingMapper, VideoTeaching> implements VideoTeachingDao {
}
