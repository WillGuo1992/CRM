package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.Customer;
import domain.LinkMan;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import service.CustomerService;
import service.LinkManService;
import sun.plugin.cache.OldCacheEntry;

import java.util.Deque;
import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 16:53
 **/
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkMan linkMan = new LinkMan();

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    private LinkManService linkManService;
    private CustomerService customerService;
    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 使用set方法的方式接收数据:
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null) {
            currPage = 1;
        }
        this.currPage = currPage;
    }

    // 使用set方法接受每页显示记录数
    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }
    public String findAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        if (linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())) {
            detachedCriteria.add(Restrictions.like("lkm_name", linkMan.getLkm_name(), MatchMode.ANYWHERE));
        }
        if (linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())) {
            detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
        }
        PageBean<LinkMan> pageBean=  linkManService.findByPage(detachedCriteria, currPage, pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String saveUI() {
        List<Customer> list =  customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    public String save() {
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    public String edit() {
        List<Customer> list =  customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        linkMan = linkManService.findById(linkMan.getLkm_id());
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }

    public String update() {
        linkManService.update(linkMan);
        return "updateSuccess";
    }

    public String delete() {
        linkMan = linkManService.findById(linkMan.getLkm_id());
        linkManService.delete(linkMan);
        return "deleteSuccess";
    }


}
