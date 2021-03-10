package com.haiyan.deflower.service;

import com.haiyan.deflower.dto.request.FlowerQuery;
import com.haiyan.deflower.pojo.Flower;
import com.haiyan.deflower.pojo.PageList;

/**
 * @author haiyan
 */
public interface FlowerService {
    /**
     * 新增花
     * @param flower 花
     * @return 花id
     */
    Long addFlower(Flower flower);

    /**
     * 修改花
     * @param flower 花
     * @return 结果
     */
    Boolean updateFlower(Flower flower);


    /**
     * 分页查询
     * @param query 参数
     * @return 分页数据
     */
    PageList<Flower> listFlower(FlowerQuery query);
}
