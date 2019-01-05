package com.itheima.bos.dao.impl;


import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.User;
import com.itheima.bos.utils.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class IUserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String hql = "FROM User  u  WHERE u.username=? AND u.password=?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
}
