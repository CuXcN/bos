package com.itheima.bos.dao;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

import java.util.List;

public interface IRegionDao {

    public void saveOrUpdate(Region region);

    public void pageQuery(PageBean pageBean);

    public List<Region> findListByQ(String q);

    public List<Region> findAll();

}
