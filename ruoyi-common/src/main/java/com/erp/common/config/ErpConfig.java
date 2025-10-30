package com.erp.common.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author erp
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "erp")
public class ErpConfig
{

	@Getter
	private static String aliAccessKey;

	@Getter
	private static String aliSecret;

	@Getter
	private static String aliSignName;

    /** api域名地址，如：https://x.x.x/api/ */
	@Getter
    private static String apiUrl;

    @Getter
    private static String wapUrl;

    @Getter
    private static String pcUrl;

    /** 项目名称 */
    private String name;

    /** 版本 */
    private String version;

    /** 版权年份 */
    private String copyrightYear;

    /** 实例演示开关 */
    private boolean demoEnabled;

    /** 上传路径 */
    @Getter
    private static String profile;

    /** 获取地址开关 */
    @Getter
    private static boolean addressEnabled;

	public void setAliSignName(String aliSignName)
	{
		ErpConfig.aliSignName = aliSignName;
	}

	public void setAliAccessKey(String aliAccessKey)
	{
		ErpConfig.aliAccessKey = aliAccessKey;
	}

	public void setAliSecret(String aliSecret)
	{
		ErpConfig.aliSecret = aliSecret;
	}

    public void setApiUrl(String apiUrl)
    {
        ErpConfig.apiUrl = apiUrl;
    }

    public void setWapUrl(String wapUrl)
    {
        ErpConfig.wapUrl = wapUrl;
    }

    public void setPcUrl(String pcUrl)
    {
        ErpConfig.pcUrl = pcUrl;
    }

    public void setProfile(String profile)
    {
        ErpConfig.profile = profile;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        ErpConfig.addressEnabled = addressEnabled;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
