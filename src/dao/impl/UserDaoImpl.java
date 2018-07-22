package dao.impl;

import dao.UserDao;
import domain.User;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-21 02:45
 **/
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password = ?", user.getUser_code(), user.getUser_password());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
