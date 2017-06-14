package jjzhu.study.util;

import javax.servlet.http.Part;

/**
 * Created by hzzhujiajun on 2017/6/13.
 */
public class HttpUtils {

    public static String getFileName(Part part){
        String contentDispositionHeader = part.getHeader("content-disposition");
        if(contentDispositionHeader == null || contentDispositionHeader.isEmpty()){
            return null;
        }
        String [] elements = contentDispositionHeader.split(";");

        for(String elem: elements){
            if(elem.trim().startsWith("filename")){
                return elem.substring(elem.indexOf("=")+1).trim().replace("\"","");
            }
        }
        return null;
    }
}
