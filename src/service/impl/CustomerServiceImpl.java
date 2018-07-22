package service.impl;

import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:35
 **/
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
        PageBean<Customer> pageBean = new PageBean();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = customerDao.findCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        pageBean.setTotalPage(totalPage);
        Integer begin = (currPage - 1) * pageSize;
        List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
