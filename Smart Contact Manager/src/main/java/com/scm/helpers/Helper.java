package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        //if we logged in with username password how can we fetch email
        if (authentication instanceof OAuth2AuthenticationToken) {
            var aoOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aoOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2user = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {
                //if we logged in with google how can we fetch email
                System.out.println("Getting email from google");
                username = oauth2user.getAttribute("email").toString();

            } else if (clientId.equalsIgnoreCase("github")) {
                //if we logged in with github how can we fetch email
                System.out.println("Getting email from github");
                username=oauth2user.getAttribute("email") != null ? oauth2user.getAttribute("email").toString()
                        : oauth2user.getAttribute("login").toString() + "@gmail.com";
            }

            return username;

        } else {
            return authentication.getName();
        }
    }
}
