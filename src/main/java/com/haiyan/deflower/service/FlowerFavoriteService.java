package com.haiyan.deflower.service;

import com.haiyan.deflower.pojo.FlowerFavorite;

/**
 * @author haiyan
 */
public interface FlowerFavoriteService {

    /**
     * 添加收藏
     * @param flowerFavorite 参数
     * @return id
     */
    Long addFlowerFavorite(FlowerFavorite flowerFavorite);

    /**
     * 删除收藏
     * @param id id
     * @return 结果
     */
    Boolean deletedFlowerFavorite(Long id);

    /**
     * 验证是否收藏
     * @param favoriteId 花id
     * @return 结果
     */
    Boolean verify(Long favoriteId);

}
