package ru.spring.security.project.customVoter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Calendar;
import java.util.Collection;

public class NightVoter implements AccessDecisionVoter {

    private String votePrefix = "ROLE_";

    public static final int SUNRISE_HOUR = 9;
    public static final int SUNSET_HOUR = 21;

    public String getVotePrefix() {
        return votePrefix;
    }

    public void setVotePrefix(String votePrefix) {
        this.votePrefix = votePrefix;
    }

    public boolean supports(ConfigAttribute configAttribute) {
        if(configAttribute.getAttribute() != null &&
           configAttribute.getAttribute().startsWith(getVotePrefix()))
        {
            return true;
        }

        return false;
    }

    public int vote(Authentication authentication, Object o, Collection attributes) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if(hour <= SUNRISE_HOUR && hour >= SUNRISE_HOUR) {
            return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    public boolean supports(Class aClass) {
        return true;
    }
}
