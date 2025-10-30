package com.erp.web.controller.common;

import com.erp.common.core.domain.AjaxResult;
import com.erp.common.exception.CustomException;
import com.erp.common.utils.AliVodUtils;
import com.erp.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 阿里视频点播
 *
 * @author erp
 */
@RestController
@RequestMapping("ali/vod")
public class AliVodController
{
    private static final Logger log = LoggerFactory.getLogger(AliVodController.class);

    @Autowired
    private ServerConfig serverConfig;

	@GetMapping("deleteVideo/{videoId}")
	public AjaxResult deleteVideo(@PathVariable("videoId") String videoId)
	{
		try {
			AliVodUtils.deleteVideo(videoId);
			return AjaxResult.success();
		} catch (Exception e) {
			log.error("ali vod deleteVideo error, ", e);
			throw new CustomException("删除视频失败");
		}
	}

	/**
	 * 获取播放信息
	 */
	@GetMapping("getPalyInfo/{videoId}")
	public AjaxResult getPalyInfo(@PathVariable("videoId") String videoId)
	{
		try {
			return AjaxResult.success(AliVodUtils.getPlayInfo(videoId));
		} catch (Exception e) {
			throw new CustomException("获取播放信息失败");
		}
	}

    /**
	 * 获取音/视频上传地址和凭证
	 */
    @GetMapping("createUploadVideo")
    public AjaxResult createUploadVideo(String fileTitle, String fileName) {
		try {
			return AjaxResult.success(AliVodUtils.createUploadVideo(fileTitle, fileName));
		} catch (Exception e) {
			throw new CustomException("获取音/视频上传地址和凭证失败");
		}
    }

	/**
	 * 刷新音/视频上传凭证
	 */
	@GetMapping("refreshUploadVideo/{videoId}")
	public AjaxResult refreshUploadVideo(@PathVariable("videoId") String videoId) {
		try {
			return AjaxResult.success(AliVodUtils.refreshUploadVideo(videoId));
		} catch (Exception e) {
			throw new CustomException("刷新音/视频上传凭证失败");
		}
	}
}
