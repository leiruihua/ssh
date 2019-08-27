/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename VagueQueryUser.java
 * @time 2017年5月12日 上午10:40:20
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.pojo;
/**
 *<简述功能>用于模糊查询
 *<功能详细描述>
 * @see
 * @since
 */

public class VagueQueryUser {
	private String userName;
	private String userSex;
	private String userAgeStart;
	private String userAgeStop;
	private String userPhone;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserAgeStart() {
		return userAgeStart;
	}
	public void setUserAgeStart(String userAgeStart) {
		this.userAgeStart = userAgeStart;
	}
	public String getUserAgeStop() {
		return userAgeStop;
	}
	public void setUserAgeStop(String userAgeStop) {
		this.userAgeStop = userAgeStop;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
}
