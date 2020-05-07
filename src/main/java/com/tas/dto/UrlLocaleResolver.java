package com.tas.dto;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class UrlLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String uri = httpServletRequest.getRequestURI();
        System.out.println("URI= " + uri);
        String param = httpServletRequest.getParameter("language");
        Locale locale = null;
        if (param == null) {
            param = "vi";
        }
        //English
        if (param.equals("en")){
            locale = Locale.ENGLISH;
        }
        //France
        else if (param.equals("fr")){
            locale= Locale.FRANCE;
        }
        //Japanese
        else if (param.equals("ja")){
            locale =Locale.JAPANESE;
        }
        //Vietnamese
        else if (param.equals("vi")){
            locale = new Locale("vi","VN");
        }
        if (locale==null){
            locale = (Locale)httpServletRequest.getSession().getAttribute("test");
            if (locale==null){
                locale=Locale.ENGLISH;
            }
        }
        if(param!=null){
            httpServletRequest.getSession().setAttribute("test",locale);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
