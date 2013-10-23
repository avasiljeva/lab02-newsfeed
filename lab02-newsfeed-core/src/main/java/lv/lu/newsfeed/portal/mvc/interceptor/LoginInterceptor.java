package lv.lu.newsfeed.portal.mvc.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lv.lu.newsfeed.portal.mvc.Constants;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor for preventing non-authenticated access. 
 * 
 * This bean is defined in Spring MVC configuration file: 
 * \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\WEB-INF\newsfeed-servlet.xml
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    // do not check requests that are defined in this list
    private List<String> ignoreList;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
    throws Exception
    {        
        if (ignoreList == null || !ignoreList.contains(request.getPathInfo())){
            Object user = request.getSession().getAttribute(Constants.SessionAttribute.SESSION_USER);
            if (user == null){
                // user is not logged in, do not let access page
                response.sendRedirect(request.getContextPath());
                return false;
            }
            return true;
        }
        else{
            return true;
        }
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }
}