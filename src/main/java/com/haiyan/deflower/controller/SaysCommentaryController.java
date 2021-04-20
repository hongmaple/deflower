package com.haiyan.deflower.controller;

import com.haiyan.deflower.dto.request.SaysCommentaryQuery;
import com.haiyan.deflower.pojo.AjaxResult;
import com.haiyan.deflower.pojo.SaysCommentary;
import com.haiyan.deflower.service.SaysCommentaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author haiyan
 */
@Api(tags="花语解说")
@RestController
@RequestMapping("/SaysCommentary")
public class SaysCommentaryController {
    private final SaysCommentaryService saysCommentaryService;

    public SaysCommentaryController(SaysCommentaryService saysCommentaryService) {
        this.saysCommentaryService = saysCommentaryService;
    }

    /**
     * 新增花语解说
     * @param saysCommentary 花语解说
     * @return 花语解说id
     */
    @PostMapping()
    @ApiOperation("新增花语解说")
    public AjaxResult addSaysCommentary(@RequestBody SaysCommentary saysCommentary) {
        AjaxResult ajaxResult = AjaxResult.success(saysCommentaryService.addSaysCommentary(saysCommentary));
        return ajaxResult;
    }

    /**
     * 修改花语解说
     * @param saysCommentary 花语解说
     * @return 结果
     */
    @PutMapping()
    @ApiOperation("修改花语解说")
    public AjaxResult updateSaysCommentary(@RequestBody SaysCommentary saysCommentary) {
        AjaxResult ajaxResult = AjaxResult.success(saysCommentaryService.updateSaysCommentary(saysCommentary));
        return ajaxResult;
    }


    /**
     * 删除花语解说
     * @param id 花语解说id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除花语解说")
    public AjaxResult deletedSaysCommentary(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success(saysCommentaryService.deletedSaysCommentary(id));
        return ajaxResult;
    }

    /**
     * 加载花语解说
     * @param query SaysCommentaryQuery
     * @return SaysCommentarys
     */
    @PostMapping("/list")
    @ApiOperation("加载花语解说")
    public AjaxResult listSaysCommentary(@RequestBody SaysCommentaryQuery query) {
        AjaxResult ajaxResult = AjaxResult.success(saysCommentaryService.listSaysCommentary(query));
        return ajaxResult;
    }
}
