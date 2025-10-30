package com.erp.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.erp.common.config.ErpConfig;
import com.erp.common.utils.vo.AliVideoInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AliVodUtils {

	public static void main(String[] args) {
		try {
//			getPlayInfo("369b46e357d84cf6a0375475111f2c34");
			CreateUploadVideoResponse r = createUploadVideo("test", "test.mp4");
			System.out.println(r);
			RefreshUploadVideoResponse r2 = refreshUploadVideo(r.getVideoId());
			System.out.println(r2);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 获取音/视频上传地址和凭证
	 *
	 * @param fileTitle 音/视频标题
	 * @param fileName 音/视频源文件名。必须带扩展名，且扩展名不区分大小写。
	 * @return CreateUploadVideoResponse 获取音/视频上传地址和凭证响应数据
	 * @throws Exception
	 */
	public static CreateUploadVideoResponse createUploadVideo(String fileTitle, String fileName) throws Exception {
		CreateUploadVideoRequest request = new CreateUploadVideoRequest();
		request.setTitle(fileTitle);
		request.setFileName(fileName);

		//UserData，用户自定义设置参数，用户需要单独回调URL及数据透传时设置(非必须)
		//JSONObject userData = new JSONObject();

		//UserData回调部分设置
		//消息回调设置，指定时以此为准，否则以全局设置的事件通知为准
		//JSONObject messageCallback = new JSONObject();
		//设置回调地址
		//messageCallback.put("CallbackURL", "http://192.168.0.1/16");
		//设置回调方式，默认为http
		//messageCallback.put("CallbackType", "http");
		//userData.put("MessageCallback", messageCallback.toJSONString());

		//UserData透传数据部分设置
		//用户自定义的扩展字段，用于回调时透传返回
		//JSONObject extend = new JSONObject();
		//extend.put("MyId", "user-defined-id");
		//userData.put("Extend", extend.toJSONString());

		//request.setUserData(userData.toJSONString());

		DefaultAcsClient client = initVodClient();
		return client.getAcsResponse(request);
	}

	/**
	 * 刷新音/视频上传凭证
	 * @return RefreshUploadVideoResponse 刷新音/视频上传凭证响应数据
	 * @throws Exception
	 */
	public static RefreshUploadVideoResponse refreshUploadVideo(String videoId) throws Exception {
		RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
		request.setVideoId(videoId);
		DefaultAcsClient client = initVodClient();
		return client.getAcsResponse(request);
	}

	/**
	 * 获取视频信息
	 *
	 * @param videoId
	 * @return
	 * @throws ClientException
	 */
	public static AliVideoInfo getPlayInfo(String videoId) {
		return getPlayInfo(videoId, null);
	}

	/**
	 * 删除视频
	 */
	public static void deleteVideo(String videoId) throws ClientException {
		DeleteVideoRequest request = new DeleteVideoRequest();
		//支持传入多个视频ID，多个用逗号分隔
		request.setVideoIds(videoId);
		DefaultAcsClient client = initVodClient();
		client.getAcsResponse(request);
	}

	/**
	 * 获取视频信息
	 *
	 * @param videoId 视频ID
	 * @param previewTime 试看时长(秒)
	 * @return
	 * @throws ClientException
	 */
	public static AliVideoInfo getPlayInfo(String videoId, Long previewTime) {
		AliVideoInfo r = new AliVideoInfo();
		r.setVideoId(videoId);
		DefaultAcsClient client = initVodClient();
		GetPlayInfoResponse response = new GetPlayInfoResponse();
		try {
			GetPlayInfoRequest request = new GetPlayInfoRequest();
			request.setVideoId(videoId);
			if (previewTime != null) {
				JSONObject playConfig = new JSONObject();
				//视频点播试看时长，单位为秒。最小值1
				playConfig.put("PreviewTime", previewTime);
				request.setPlayConfig(playConfig.toJSONString());
			}
			response = client.getAcsResponse(request);

			List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
			//播放地址
			for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
				r.setPlayUrl(playInfo.getPlayURL());
				r.setDuration(Double.valueOf(playInfo.getDuration()).longValue());
				System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
				System.out.print("PlayInfo.Duration = " + playInfo.getDuration() + "(秒)\n");
			}
			//Base信息
			System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
			r.setCoverURL(response.getVideoBase().getCoverURL());
			System.out.print("VideoBase.CoverURL = " + r.getCoverURL() + "\n");
		} catch (Exception e) {
			System.out.print("ErrorMessage = " + e.getLocalizedMessage());
			log.error("Ali vod getPlayInfo failed. ", e);
		}
		System.out.print("RequestId = " + response.getRequestId() + "\n");
		return r;
	}

	//填入AccessKey信息
	public static DefaultAcsClient initVodClient() {
		String regionId = "cn-shanghai";  // 点播服务接入地域
		DefaultProfile profile = DefaultProfile.getProfile(regionId, ErpConfig.getAliAccessKey(), ErpConfig.getAliSecret());
		DefaultAcsClient client = new DefaultAcsClient(profile);
		return client;
	}
}
