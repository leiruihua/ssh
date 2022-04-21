package com.beichende.assess.trade.service;

import java.util.List;

import com.beichende.assess.trade.pojo.User;
import com.beichende.assess.trade.pojo.VagueQueryUser;
import com.beichende.assess.trade.web.dto.UserDto;


/**
 * <简述功能>
 * <功能详细描述>
 *
 * @see
 */
public interface IUserService {
    //查询部分用户
    public List<UserDto> list(VagueQueryUser vagueQueryUser, Integer pageSize, Integer pageNumber, String sort, String order) throws Exception;

    //增加用户
    public void addUser(User user) throws Exception;

    //批量删除用户
    public void deleteUser(String ids) throws Exception;

    //查询单个用户
    public List<User> queryAngleUser(String userNo) throws Exception;

    //修改单个用户
    public void editAngleUser(User user) throws Exception;

    //模糊查询用户总数
    public long portionList(VagueQueryUser vagueQueryUser) throws Exception;
}
