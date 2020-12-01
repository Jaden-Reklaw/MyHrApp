package com.astontech.hr.services;


import com.astontech.hr.domain.User;

public interface UserService {
    Iterable<User> listAllUsers();
    User getUserById(Integer id);
    User saveUser(User user);
    Iterable<User> saveUserList(Iterable<User> userIterable);
    void deleteUserById(Integer id);
}
