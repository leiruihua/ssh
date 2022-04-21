package com.beichende.assess.trade.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * <简述功能>  在容器初始化的时候创建一个sessionFactory
 * <功能详细描述>
 *
 * @see
 */
public class BaseDao<T> {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    protected Class<?> entityClass;

    public BaseDao() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<?>) type.getActualTypeArguments()[0];
    }

    //对子类提供一个获取session工厂的当前session的方法
    protected Session getSession() throws Exception {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T t) throws Exception {
        return getSession().save(t);
    }

    public SessionFactory getSessionFactory() throws Exception {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) throws Exception {
        this.sessionFactory = sessionFactory;
    }
}  
