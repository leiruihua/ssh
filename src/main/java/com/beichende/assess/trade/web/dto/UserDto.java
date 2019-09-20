/**
 * @author 雷瑞铧
 * @version 1.0.0
 * @filename User.java
 * @time 2017年5月8日 下午4:17:11
 * @copyright(C) 2017 深圳市北辰德科技股份有限公司
 */
package com.beichende.assess.trade.web.dto;


/**
 * <简述功能>
 * <功能详细描述>
 *
 * @see
 */
public class UserDto {
    private Integer userNo;
    private String userName;
    private String userSex;
    private Integer userAge;
    private String userPhone;

    public Integer getUserNo() {
        return userNo;
    }


    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }


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


    public Integer getUserAge() {
        return userAge;
    }


    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }


    public String getUserPhone() {
        return userPhone;
    }


    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
