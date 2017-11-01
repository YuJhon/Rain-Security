package com.jhon.rain.utils;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * <p>功能描述</br> Redis分布式获取锁和释放锁 </p>
 *  <pre>
 *    具体请参考：https://www.ctolib.com/topics-125801.html
 *  </pre>
 * @author jiangy19
 * @version v1.0
 * @FileName RedisTools
 * @date 2017/11/1 14:01
 */
public class RedisTools {

	private static final String LOCK_SUCCESS = "OK";

	private static final String SET_IF_NOT_EXIST = "NX";

	private static final String SET_WITH_EXPIRE_TIME = "PX";

	private static final Long RELEASE_SUCCESS = 1L;

	/**
	 * <pre>尝试获取分布式锁</pre>
	 *
	 * @param jedis      Redis客户端
	 * @param lockKey    锁
	 * @param requestId  请求标识
	 * @param expireTime 过期时间
	 * @return 是否获取成功
	 */
	public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
		String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
		if (LOCK_SUCCESS.equals(result)) {
			return true;
		}
		return false;
	}

	/**
	 * <pre>释放分布式锁</pre>
	 *
	 * @param jedis     Redis客户端
	 * @param lockKey   锁
	 * @param requestId 请求标识
	 * @return 是否释放锁成功
	 */
	public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
		String script = "if redis.call('get',KEYS[1] then return redis.call('del',KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
		if (RELEASE_SUCCESS.equals(result)) {
			return true;
		}
		return false;
	}

}
