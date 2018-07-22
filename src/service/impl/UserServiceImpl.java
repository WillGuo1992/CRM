package service.impl;

import dao.UserDao;
import domain.User;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import utils.MD5Utils;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-21 02:42
 **/
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao ;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void regist(User user) {
        user.setUser_state("1");
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        return userDao.login(user);
    }

    @Override
    public List<User> findAll() {
       return userDao.findAll();
    }
}
