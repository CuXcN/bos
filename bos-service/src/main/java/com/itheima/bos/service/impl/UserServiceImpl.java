package com.itheima.bos.service.impl;

import com.itheima.bos.dao.IUserDao;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.IUserService;
import com.itheima.bos.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;


    @Override
    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //使用md5jiami
        password = MD5Utils.md5(password);
        User user1 = userDao.findByUsernameAndPassword(username, password);
        return user1;
    }

    @Override
    public void editPassword(String id, String password) {
        //使用MD5加密密码
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editpassword", password,id);
    }
}
