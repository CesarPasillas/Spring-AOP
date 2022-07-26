package com.pasnys.aop.example.service;

import com.pasnys.aop.example.data.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ClientBussines {

    @Autowired
    private ClientDao dao;

    public String calculateSomething() throws Exception{
        //Business Logic
       return dao.retrieveSomenthing();
    }

}

