package com.haiyan.deflower.controller;

import com.haiyan.deflower.dto.request.VideoTeachingQuery;
import com.haiyan.deflower.pojo.AjaxResult;
import com.haiyan.deflower.pojo.VideoTeaching;
import com.haiyan.deflower.service.VideoTeachingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author haiyan
 */
@Api(tags="视频教学")
@RestController
@RequestMapping("/VideoTeaching")
public class VideoTeachingController {
    @Autowired
    private VideoTeachingService videoTeachingService;

    /**
     * 添加视频教学
     * @param videoTeaching 视频
     * @return 视频id
     */
    @PostMapping
    @ApiOperation("添加视频教学")
    public AjaxResult addVideoTeaching(@RequestBody VideoTeaching videoTeaching) {
        Long id = this.videoTeachingService.addVideoTeaching(videoTeaching);
        AjaxResult ajaxResult = AjaxResult.success("添加视频教学成功",id);
        return ajaxResult;
    }

    /**
     * 修改视频
     * @param videoTeaching 视频
     * @return 结果
     */
    @PutMapping
    @ApiOperation("添加视频教学")
    public AjaxResult updateVideoTeaching(@RequestBody VideoTeaching videoTeaching) {
        Boolean aBoolean = videoTeachingService.updateVideoTeaching(videoTeaching);
        AjaxResult ajaxResult;
        if (aBoolean){
            ajaxResult = AjaxResult.success("添加视频教学成功",aBoolean);
        }else {
            ajaxResult = AjaxResult.success("添加视频教学失败", aBoolean);
        }
        return ajaxResult;
    }

    /**
     * 加载视频
     * @param query 视频
     * @return 视频
     */
    @PostMapping("/list")
    @ApiOperation("添加视频教学")
    public AjaxResult videoTeachingList(@RequestBody VideoTeachingQuery query) {
        AjaxResult ajaxResult = AjaxResult.success("添加视频教学成功",videoTeachingService.videoTeachingList(query));
        return ajaxResult;
    }

    /**
     *
     * @param id 视频id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除视频教学")
    public AjaxResult deletedVideoTeaching(@PathVariable Long id) {
        Boolean aBoolean = videoTeachingService.deletedVideoTeaching(id);
        AjaxResult ajaxResult;
        if (aBoolean){
            ajaxResult = AjaxResult.success("添加视频教学成功",aBoolean);
        }else {
            ajaxResult = AjaxResult.success("添加视频教学失败", aBoolean);
        }
        return ajaxResult;
    }
}
