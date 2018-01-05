package jjzhu.study.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zhujiajunup on 2017/6/13.
 */
/*@WebFilter(filterName = "AutoCorrectFilter", urlPatterns = { "*//*" })*/
public class AutoCorrectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        AutoCorrectHttpServletRequestWrapper wrapper = new AutoCorrectHttpServletRequestWrapper(httpServletRequest);
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }

    class AutoCorrectHttpServletRequestWrapper extends HttpServletRequestWrapper{

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request
         * @throws IllegalArgumentException if the request is null
         */
        private HttpServletRequest httpServletRequest;
        public AutoCorrectHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.httpServletRequest = request;
        }

        @Override
        public String getParameter(String name){
            return autoCorrect(httpServletRequest.getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name){
            return autoCorrect(httpServletRequest.getParameterValues(name));
        }
    }

    private String autoCorrect(String value){
        if(value == null){
            return null;
        }
        value = value.trim();
        int length = value.length();
        boolean lastCharWasSpace = false;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < length; i++){
            char c = value.charAt(i);
            if(c == ' '){
                if(!lastCharWasSpace){
                    sb.append(c);
                }
                lastCharWasSpace = true;
            }else {
                sb.append(c);
                lastCharWasSpace = false;
            }

        }
        return sb.toString();
    }

    private String [] autoCorrect(String [] values){
        if(values != null){
            int length = values.length;
            for(int i=0; i < length; i++){
                values[i] = autoCorrect(values[i]);
            }
            return values;
        }
        return null;
    }

    private Collection<String []> autoCorrect(Collection<String []> valueCollection){
       Collection<String[]> newCollection = new ArrayList<>(valueCollection.size());
       for(String [] values: valueCollection){
           newCollection.add(autoCorrect(values));
       }
       return newCollection;
    }
}
