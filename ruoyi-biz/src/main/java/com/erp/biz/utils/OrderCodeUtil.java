package com.erp.biz.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.erp.common.core.redis.RedisCache;
import com.erp.common.core.redis.RedisLockManager;
import com.erp.common.exception.CustomException;
import com.erp.common.utils.DateUtils;
import com.erp.common.utils.spring.SpringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCodeUtil {

	/**
	 * 生成评分流水号
	 *
	 * @return String
	 */
	public static String getNextScoreSerialNumber() {
		String now = DateUtils.dateTimeNow("yyyyMMddHHmm");
		Integer no = getNextSerialNumber("score:serial:" + now);
		return String.format("%s%05d", now, no);
	}

	/**
	 * <p>
	 * 自增序列号
	 * </p>
	 */
	private static Integer getNextSerialNumber(String key) {
		String lockKey = key + ":lock";
		RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
		RedisLockManager redisLockManager = SpringUtils.getBean(RedisLockManager.class);
		boolean flag = redisLockManager.getLock(lockKey, 60, TimeUnit.SECONDS);
		if (flag) {
			Integer serialNum = redisCache.getCacheObject(key);
			if (null == serialNum) {
				serialNum = 1;
			}
			redisCache.setCacheObject(key, serialNum + 1);
			redisCache.expire(key, 24, TimeUnit.HOURS);
			redisLockManager.unLock(lockKey);
			return serialNum;
		} else {
			log.error("获取锁失败: " + lockKey);
			throw new CustomException("获取锁失败");
		}
	}

}
