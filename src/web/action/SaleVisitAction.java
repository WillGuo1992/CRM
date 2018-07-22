package web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.SaleVisitDao;
import domain.PageBean;
import domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.SaleVisitService;

import java.util.Date;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 19:09
 **/
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
    private SaleVisit saleVisit = new SaleVisit();

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    private SaleVisitService saleVisitService ;

    public void setSaleVisitService(SaleVisitService saleVisitService) {
        this.saleVisitService = saleVisitService;
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

    private Date visit_end_time;

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public String findAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        if (saleVisit.getVisit_time() != null) {
            detachedCriteria.add(Restrictions.gt("visit_time", saleVisit.getVisit_time()));
        }
        if (visit_end_time != null) {
            detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
        }
        PageBean<SaleVisit> pageBean=  saleVisitService.findByPage(detachedCriteria, currPage, pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String saveUI() {
        return "saveUI";
    }

    public String save() {
        saleVisitService.save(saleVisit);
        return "saveSuccess";
    }
}
