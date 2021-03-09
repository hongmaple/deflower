package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 花
 * @author haiyan
 */
@Data
@Table(name = "tb_flower")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 图片地址
     */
    private String images;
    /**
     * 价格
     */
    private Long price;
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