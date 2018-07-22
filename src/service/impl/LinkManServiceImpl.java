package service.impl;

import dao.LinkManDao;
import domain.LinkMan;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import service.LinkManService;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 16:55
 **/

@Transactional
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }


    @Override
    public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean<>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = linkManDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        Integer begin = (currPage - 1) * pageSize;
        List<LinkMan> list = linkManDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {
        return linkManDao.findById(lkm_id);
    }

    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }
}
