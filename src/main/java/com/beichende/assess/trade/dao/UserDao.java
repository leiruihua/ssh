package com.beichende.assess.trade.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.beichende.assess.trade.pojo.User;
import com.beichende.assess.trade.pojo.VagueQueryUser;
import com.beichende.assess.trade.web.dto.UserDto;

/**
 * 实现用户的增删改查的方法
 * <功能详细描述>
 *
 * @see
 */
@Repository(value = "userDao")
public class UserDao extends BaseDao<User> {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    UserDao() {
        logger.info("创建UserDao实例");
    }

    //查找用户
    public List<UserDto> list(VagueQueryUser vagueQueryUser, Integer pageSize, Integer pageNumber, String sort, String order) throws Exception {
        Criteria c = Common(vagueQueryUser);
        c.setFirstResult((pageSize - 1) * pageNumber);
        c.setMaxResults(pageNumber);

        if (("asc").equals(order)) {
            c.addOrder(Order.asc(sort));
        }
        if (("desc").equals(order)) {
            c.addOrder(Order.desc(sort));
        }

        @SuppressWarnings("unchecked")
        List<User> list = c.list();
        List<UserDto> list2 = new ArrayList<UserDto>();
        Map<Integer, Object> userMap = new HashMap<Integer, Object>();
        for (int i = 0; i < list.size(); i++) {
            UserDto user = new UserDto();
            user.setUserNo(list.get(i).getUserNo());
            user.setUserName(list.get(i).getUserName());
            user.setUserAge(list.get(i).getUserAge());
            user.setUserPhone(list.get(i).getUserPhone());
            user.setUserSex(1 == list.get(i).getUserSex() ? "男" : "女");

            userMap.put(i, user);
            list2.add((UserDto) userMap.get(i));
        }

        return list2;
    }

    //查找单个用户
    public List<User> queryAngleUser(String userNo) throws Exception {
        @SuppressWarnings("unchecked")
        List<User> list = getSession().createQuery("from User c where  c.userNo =" + userNo).list();
        return list;
    }

    //增加单个用户
    public void addUser(User user) throws Exception {
        getSession().save(user);
    }

    //批量删除用户
    public void deleteUser(String ids) throws Exception {
        String[] strArray = ids.split(",");//将字符串转化为数组
        String hql = "";
        for (int i = 0; i < strArray.length; i++) {
            if (i == 0) {
                hql = "userNo =" + Integer.parseInt(strArray[i]);
            } else {
                hql = hql + " or userNo =" + Integer.parseInt(strArray[i]);
            }
        }
        Query q = getSession().createQuery("delete from User where " + hql);
        q.executeUpdate();
    }

    //修改单个用户
    public void editAngleUser(User user) throws Exception {
        getSession().update(user);
    }

    //模糊查询用户总数
    public long portionList(VagueQueryUser vagueQueryUser) throws Exception {
        Criteria c = Common(vagueQueryUser);
        //下面的这种方式就提升了性能
        c.setProjection(Projections.rowCount());
        Long total = Long.valueOf(c.uniqueResult().toString());
        return total;
    }

    //查询条件
    public Criteria Common(VagueQueryUser vagueQueryUser) throws Exception {
        Criteria c = getSession().createCriteria(User.class);

        if (!("").equals(vagueQueryUser.getUserName())) {
            c.add(Restrictions.like("userName", "%" + vagueQueryUser.getUserName() + "%"));
        }
        if (!("").equals(vagueQueryUser.getUserSex())) {
            c.add(Restrictions.eq("userSex", Integer.parseInt(vagueQueryUser.getUserSex())));
        }
        if (!("").equals(vagueQueryUser.getUserAgeStart())) {
            c.add(Restrictions.ge("userAge", Integer.parseInt(vagueQueryUser.getUserAgeStart())));//大于等于
        }
        if (!("").equals(vagueQueryUser.getUserAgeStop())) {
            c.add(Restrictions.le("userAge", Integer.parseInt(vagueQueryUser.getUserAgeStop())));//小于等于
        }
        if (!("").equals(vagueQueryUser.getUserPhone())) {
            c.add(Restrictions.like("userPhone", "%" + vagueQueryUser.getUserPhone() + "%"));
        }

        return c;
    }

}
