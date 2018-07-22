package service;

import domain.User;

public interface UserService {
    void regist(User user);

    User login(User user);
}
