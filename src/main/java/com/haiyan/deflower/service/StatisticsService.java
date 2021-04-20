package com.haiyan.deflower.service;

import com.haiyan.deflower.dto.response.AgeAnalysisVo;
import com.haiyan.deflower.dto.response.FlowerRankingVo;
import com.haiyan.deflower.dto.response.RegionalDistributionVo;
import com.haiyan.deflower.dto.response.StatisticsTopVo;

import java.util.List;

/**
 * @author maple
 */
public interface StatisticsService {

    /**
     * 首页 top 统计
     * @return 统计数据
     */
    StatisticsTopVo getStatisticsTopVo();

    /**
     * 首页年龄分布统计
     * @return 年龄分布统计
     */
    List<AgeAnalysisVo> getAgeAnalysisVo();

    /**
     * 首页地区分布统计
     * @return 年龄分布统计
     */
    RegionalDistributionVo getRegionalDistributionVo();

    /**
     * 最受欢迎的花排行
     * @return 花排行
     */
    FlowerRankingVo getFlowerRankingVo();

}
