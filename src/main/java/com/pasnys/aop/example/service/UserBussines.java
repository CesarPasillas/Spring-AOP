package com.pasnys.aop.example.service;

import com.pasnys.aop.example.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserBussines {

    @Autowired
    private UserDao dao;

    public String requestAcces(){
        //BUsiness Logic
       return dao.login();
    }
}

