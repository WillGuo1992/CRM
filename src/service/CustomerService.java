package service;

import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:34
 **/
public interface CustomerService {
    PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(Customer customer);

    Customer findById(Long cust_id);

    void update(Customer customer);

    void delete(Customer customer);

    List<Customer> findAll();
}
