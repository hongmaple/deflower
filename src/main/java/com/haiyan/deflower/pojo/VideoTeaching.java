package com.haiyan.deflower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author haiyan
 */
@Data
@TableName("tb_video_teaching")
public class VideoTeaching {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String pic;
    private String url;
    private Boolean isCharge;
    private double price;
    private Long creatorId;
    private Integer creatorType;
    private Integer status;
}
