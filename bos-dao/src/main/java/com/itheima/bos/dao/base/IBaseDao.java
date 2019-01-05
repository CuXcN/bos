package com.itheima.bos.dao.base;

import com.itheima.bos.utils.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * 持久层通用接口
 *
 * @param <T>
 * @author zhaoqx
 */

public interface IBaseDao<T> {


    public void save(T entity);

    public void delete(T entity);

    public void update(T entity);

    public T findById(Serializable id);

    public List<T> findAll();

    public void executeUpdate(String queryName, Object... objects);

    void pageQuery(PageBean pageBean);
}
