package com.asksunny.demo.persistence;


import com.asksunny.demo.domain.Users;


public interface UsersMapper {


    List<Users> getAllUsers();

    void insertUsers(Users users);

    Users getUsersBykey(Users users);

    void updateUsersByKey(Users users);

    void deleteUsersByKey(Users users);

}