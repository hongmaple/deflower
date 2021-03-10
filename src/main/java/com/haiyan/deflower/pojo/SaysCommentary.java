package com.haiyan.deflower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 花语解说
 * @author haiyan
 */
@TableName("tb_says_commentary")
@Data
public class SaysCommentary {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
