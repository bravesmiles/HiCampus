/**
 * 
 */
package com.guo.entity;

/**
 * description: cateTime：2014-5-5下午01:43:35 updateTime： author：ddcat
 */
public class Order {
	private int t_id; // 表自增ID
	private int userId; // 用户ID
	private String deviceId; // 用户ID
	private String adminId; // 审批管理员IP
	private int status; // 兑换状态 0: 未兑换 1：已经兑换
	private String createTime; // 记录创建时间
	private String userIp;// 兑换时的IP地址
	private String way;// 兑换方式
	private int count;// 兑换金额，整数元

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int tId) {
		t_id = tId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
