package service;

import domain.PageBean;
import domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 19:10
 **/
public interface SaleVisitService {
    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
