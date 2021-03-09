package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 花语解说
 * @author haiyan
 */
@Table(name = "tb_says_commentary")
@Data
public class SaysCommentary {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 花名
     */
    private String flowerName;

    /**
     * 图片
     */
    private String image;

    /**
     * 解说
     */
    private String narrate;
}
