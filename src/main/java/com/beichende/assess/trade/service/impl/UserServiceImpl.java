package com.beichende.assess.trade.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.beichende.assess.trade.dao.UserDao;
import com.beichende.assess.trade.pojo.User;
import com.beichende.assess.trade.pojo.VagueQueryUser;
import com.beichende.assess.trade.service.IUserService;
import com.beichende.assess.trade.web.dto.UserDto;


/**
 * <简述功能>Service层
 * <功能详细描述>
 *
 * @see
 */

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserDao userDao;

    UserServiceImpl() {
        logger.info("开始创建UserServiceImpl实例");
    }

    //查询用户信息
    @Override
    public List<UserDto> list(VagueQueryUser vagueQueryUser, Integer pageSize, Integer pageNumber, String sort, String order) throws Exception {
        List<UserDto> c = userDao.list(vagueQueryUser, pageSize, pageNumber, sort, order);
        return c;
    }

    //增加单个用户
    @Override
    public void addUser(User user) throws Exception {
        logger.info("增加单个用户开始");
        userDao.addUser(user);
        logger.info("增加单个用户结束");
    }

    //批量删除用户
    @Override
    public void deleteUser(String ids) throws Exception {
        logger.info("批量用户开始");
        userDao.deleteUser(ids);
        logger.info("批量删除用户结束");
    }

    //查询单个用户
    @Override
    public List<User> queryAngleUser(String userNo) throws Exception {
        return userDao.queryAngleUser(userNo);
    }

    //修改单个用户
    @Override
    public void editAngleUser(User user) throws Exception {
        logger.info("修改单个用户开始");
        userDao.editAngleUser(user);
        logger.info("修改单个用户结束");
    }

    //模糊查询用户条数
    @Override
    public long portionList(VagueQueryUser vagueQueryUser) throws Exception {
        return userDao.portionList(vagueQueryUser);

    }
}
