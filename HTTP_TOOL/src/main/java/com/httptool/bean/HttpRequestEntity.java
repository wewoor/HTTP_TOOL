package com.httptool.bean;

/**
 * 
 * This object includes request URL,request body string,
 * and request method so on property.
 * Program will makes use of this object to send request to server. 
 * 
 * @author  Ziv
 * @version  [版本号, 2013-10-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HttpRequestEntity {
    
    //request url
    private String url;
    
    //request method
    private String method;
    
    //request body String
    private String bodyString;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }
     
}
