package com.erp.common.utils.ip;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.erp.common.config.ErpConfig;
import com.erp.common.constant.Constants;
import com.erp.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 获取地址类
 *
 * @author erp
 */
@Slf4j
public class AddressUtils {

	// IP地址查询
	public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

	// 未知地址
	public static final String UNKNOWN = "X X";

	public static String getRealAddressByIP(String ip) {
		String address = UNKNOWN;
		// 内网不查询
		try {
			if (NetUtil.isInnerIP(ip)) {
				return "内网IP";
			}
		} catch(Exception e) {
			return "内网IP";
		}
		if (ErpConfig.isAddressEnabled()) {
			try {
				String rspStr = HttpUtil.createGet(IP_URL)
					.body("ip=" + ip + "&json=true", Constants.GBK)
					.execute()
					.body();
				if (StrUtil.isEmpty(rspStr)) {
					log.error("获取地理位置异常 {}", ip);
					return UNKNOWN;
				}
				Map<String, String> obj = JsonUtils.parseMap(rspStr);
				String region = obj.get("pro");
				String city = obj.get("city");
				return String.format("%s %s", region, city);
			} catch (Exception e) {
				log.error("获取地理位置异常 {}", ip);
			}
		}
		return address;
	}
}
