package com.asksunny.demo.persistence;


import com.asksunny.demo.domain.Users3;


public interface Users3Mapper {


    List<Users3> getAllUsers3();

    void insertUsers3(Users3 users3);

    void updateUsers3(Users3 users3);

    void deleteUsers3(Users3 users3);

    Users3 getUsers3Bykey(Users3 users3);

}