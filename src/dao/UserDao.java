package dao;

import domain.User;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-21 02:43
 **/
public interface UserDao extends BaseDao<User>{
    User login(User user);
}
