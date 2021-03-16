package com.haiyan.deflower.service;

import com.haiyan.deflower.dto.request.SaysCommentaryQuery;
import com.haiyan.deflower.pojo.PageList;
import com.haiyan.deflower.pojo.SaysCommentary;


/**
 * @author haiyan
 */
public interface SaysCommentaryService {
    /**
     * 新增花语解说
     * @param saysCommentary 花语解说
     * @return 花语解说id
     */
    Long addSaysCommentary(SaysCommentary saysCommentary);

    /**
     * 修改花语解说
     * @param saysCommentary 花语解说
     * @return 结果
     */
    Boolean updateSaysCommentary(SaysCommentary saysCommentary);

    /**
     * 删除花语解说
     * @param id 花语解说id
     * @return 结果
     */
    Boolean deletedSaysCommentary(Long id);

    /**
     * 加载所有花语解说
     * @param query SaysCommentaryQuery
     * @return SaysCommentarys
     */
    PageList<SaysCommentary> listSaysCommentary(SaysCommentaryQuery query);
}
