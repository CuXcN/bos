package com.itheima.bos.service.impl;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteBatch(String ids) {//1,2,3,4
        if (StringUtils.isNotBlank(ids)) {
            String[] staffIds = ids.split(",");
            for (String id : staffIds) {
                staffDao.executeUpdate("staff.delete", id);
            }
        }
    }

    /**
     * 根据id查询取派员
     */
    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

}
