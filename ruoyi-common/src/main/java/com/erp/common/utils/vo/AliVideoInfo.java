package com.erp.common.utils.vo;

import lombok.Data;

@Data
public class AliVideoInfo {

	/**
	 * 视频ID
	 */
	private String videoId;

	/**
	 * 播放地址
	 */
	private String playUrl;

	/**
	 * 时长（秒）
	 */
	private Long duration;

	/**
	 * 视频封面
	 */
	private String coverURL;
}
