package com.itheima.bos.service;

import com.itheima.bos.domain.User;

public interface IUserService {

    User login(User model);

    public void editPassword(String id, String password);

}
