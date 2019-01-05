package com.itheima.bos.web.action;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
import com.itheima.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    /**
     * 属性驱动
     */
    private int page;
    private int rows;
    @Autowired
    private IStaffService staffService;

    /**
     * 添加取派员
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }


    /**
     * 分页查询方法
     *
     * @return
     */
    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建离线对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(criteria);
        staffService.pageQuery(pageBean);

        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        JsonConfig jsonConfig = new JsonConfig();
        //指定那些属性不需要转json
        jsonConfig.setExcludes(new String[]{"currentPage", "detachedCriteria", "pageSize"});
        String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json; charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    //属性驱动，接收页面提交的ids参数
    private String ids;

    public String deleteBatch() {
        staffService.deleteBatch(ids);

        return LIST;

    }

    /**
     * 修改收派员信息
     */
    public String edit() {
        String id = model.getId();
        Staff staff = staffService.findById(id);
        staff.setHaspda(model.getHaspda());
        staff.setName(model.getName());
        staff.setStandard(model.getStandard());
        staff.setTelephone(model.getTelephone());

        staffService.update(staff);
        return LIST;
    }

}
