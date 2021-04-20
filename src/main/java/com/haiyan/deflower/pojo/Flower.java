package com.haiyan.deflower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 花
 * @author haiyan
 */
@Data
@TableName("tb_flower")
public class Flower {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类id
     */
    private Long cid;
    /**
     * 标题
     */
    private String title;
    /**
     * 花封面地址
     */
    private String images;
    /**
     * 图片列表
     */
    private String imagesList;
    /**
     * 价格
     */
    private double price;
    /**
     * 花语
     */
    private String flowerLanguage;

    /**
     * 适宜人群
     */
    private String appropriateCrowd;
    /**
     * 是否有效，逻辑删除用
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date updateTime;
}