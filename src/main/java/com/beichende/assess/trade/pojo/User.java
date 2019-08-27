/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename UserDto.java
 * @time 2017年3月9日 下午7:19:38
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *<简述功能>
 *<功能详细描述>
 * @see
 * @since
 */
@Entity
@Table(name="yolanda_userDto")
public class User {

	private Integer userNo;		//用户编号->主键
	private String userName;	//用户名	
	private Integer userSex;	//用户性别
	private Integer userAge;	//用户年龄
	private String userPhone;	//手机号码
	private String userQQ;		//用户QQ
	
	
	@Id
	@GeneratedValue(generator="seq_yolanda_userDto",strategy = GenerationType.SEQUENCE) 
    @SequenceGenerator(name = "seq_yolanda_userDto", sequenceName = "seq_yolanda_userDto" ,allocationSize =1,initialValue=1)
	@Column(name="USERNO")
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="USERSEX")
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	@Column(name="USERAGE")
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	@Column(name="USERPHONE")
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Column(name="USERQQ")
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
}
