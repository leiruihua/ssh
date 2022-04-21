package com.beichende.assess.trade.pojo;

import lombok.Data;

/**
 * <简述功能>用于模糊查询
 * <功能详细描述>
 *
 * @see
 */
@Data
public class VagueQueryUser {

    private String userName;     // 用户名
    private String userSex;      // 用户性别
    private String userAgeStart; // 用户开始年龄
    private String userAgeStop;  // 用户结束年龄
    private String userPhone;    // 用户手机号码
}
