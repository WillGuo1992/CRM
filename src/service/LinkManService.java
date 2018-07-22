package service;

import domain.LinkMan;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 16:54
 **/
public interface LinkManService {
    PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
