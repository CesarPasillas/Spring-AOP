package com.pasnys.aop.example.data;

import com.pasnys.aop.example.aspect.annotation.TrackTime;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class ClientDao {

    @TrackTime
    public String retrieveSomenthing() throws Exception{
        throw new Exception("Login Refused");

    }

    @TrackTime
    public String retrieveException() throws Exception {

        throw new Exception();
    }
}
