package com.haiyan.deflower.dto.request;

import com.haiyan.deflower.pojo.PageDomain;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏夹
 * @author haiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询收藏夹")
public class CollectionQuery extends PageDomain {
    private Integer type;
}
