package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 花
 * @author haiyan
 */
@Data
@Table(name = "tb_flower_favorite")
public class FlowerFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 花的id
     */
    private Long favoriteId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;
}
