package com.tweetapp.service;

import com.tweetapp.model.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Class Session Service
 * Used to store and fetch the username from session
 *
 * @author 841771 jaydatt
 */

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionData sessionData;

    /**
     * Used to fetch the login from session
     * <p>
     * and check the user is logged in or not
     *
     * @return token
     */
    public boolean isUserLoggedIn(String username) {
        return sessionData.getLoggedInUsers().contains(username);
    }

    /**
     * Used to store login users user name into session
     *
     * @param username
     */
    public void setLoginUserToSession(String username) {
        sessionData.getLoggedInUsers().add(username);
    }
}
