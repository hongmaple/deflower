package com.haiyan.deflower.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author haiyan
 */
@Data
@ApiModel("首页top统计")
public class StatisticsTopVo {
    private double saleroom;
    private Integer orderQuantity;
    private Integer numberGoods;
    private Integer numberVideo;
    private Integer totalNumberRegistrants;
    private Double menThan;
    private Double womenThan;
    private Integer newRegistrations;
}
