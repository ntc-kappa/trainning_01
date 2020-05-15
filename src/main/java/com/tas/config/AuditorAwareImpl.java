package com.tas.config;

import com.tas.utils.SecurityUtil;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        try {
            return SecurityUtil.getCurrentUser().getUsername();
        } catch (NullPointerException ex) {
            return "TEST";
        }
    }


}
