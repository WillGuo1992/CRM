package service.impl;

import dao.SaleVisitDao;
import domain.PageBean;
import domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import service.SaleVisitService;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 19:11
 **/
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
    private SaleVisitDao saleVisitDao;

    public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
        this.saleVisitDao = saleVisitDao;
    }

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        Integer begin = (currPage - 1) * pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
