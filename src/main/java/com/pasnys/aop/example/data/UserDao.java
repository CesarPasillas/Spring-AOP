package com.pasnys.aop.example.data;

import com.pasnys.aop.example.aspect.annotation.TrackTime;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.LoginException;

/**
 *
 */
@Repository
public class UserDao {

    @TrackTime
    public String login(){
        return "Login granted";
    }

    @TrackTime
    public String loginException() throws LoginException {
        throw new LoginException("User not have access");
    }

    @TrackTime
    public String loginDenied(){
        return "Login Denied";
    }
}
