package com.tas.dto;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class UrlLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String uri = request.getRequestURI();

        System.out.println("URI=" + uri);

        String param = request.getParameter("lang");

        Locale locale = null;

        if (param == null) {
            param = "vi";
        }

        // English
        if (param.equals("en")) {
            locale = Locale.ENGLISH;
        }
        // French
        else if (param.equals("fr")) {
            locale = Locale.FRANCE;
        }
        // Vietnamese
        else if (param.equals("vi")) {
            locale = new Locale("vi", "VN");
        }
        if (locale != null) {
            request.getSession().setAttribute("test", locale);
        }
        if (locale == null) {
            locale = (Locale) request.getSession().getAttribute("test");
            if (locale == null) {
                locale = Locale.ENGLISH;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }
}
