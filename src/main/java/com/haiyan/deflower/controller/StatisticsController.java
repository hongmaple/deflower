package com.haiyan.deflower.controller;

import com.haiyan.deflower.dto.response.AgeAnalysisVo;
import com.haiyan.deflower.dto.response.FlowerRankingVo;
import com.haiyan.deflower.dto.response.RegionalDistributionVo;
import com.haiyan.deflower.dto.response.StatisticsTopVo;
import com.haiyan.deflower.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据统计
 *
 * @author haiyan
 */
@Slf4j
@Api(tags = "数据统计")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * 首页 top 统计
     * @return 统计数据
     */
    @GetMapping("/top")
    @ApiOperation("首页 top 统计")
    public StatisticsTopVo getStatisticsTopVo() {
        return statisticsService.getStatisticsTopVo();
    }

    /**
     * 首页年龄分布统计
     * @return 年龄分布统计
     */
    @GetMapping("/ageAnalysis")
    @ApiOperation("首页年龄分布统计")
    public List<AgeAnalysisVo> getAgeAnalysisVo() {
        return statisticsService.getAgeAnalysisVo();
    }

    /**
     * 首页地区分布统计
     * @return 年龄分布统计
     */
    @GetMapping("/regionalDistribution")
    @ApiOperation("首页地区分布统计")
    public RegionalDistributionVo getRegionalDistributionVo() {
        return statisticsService.getRegionalDistributionVo();
    }

    /**
     * 最受欢迎的花排行
     * @return 花排行
     */
    @GetMapping("/flowerRanking")
    @ApiOperation("最受欢迎的花排行")
    public FlowerRankingVo getFlowerRankingVo() {
        return statisticsService.getFlowerRankingVo();
    }

}
