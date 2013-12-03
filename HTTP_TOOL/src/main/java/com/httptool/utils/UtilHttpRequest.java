package com.httptool.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httptool.constant.HttpResponse;


/**
 * 
 * HTTP协议请求发送服务
 * 
 * @author Ziv
 * 
 */
public class UtilHttpRequest {

	//Logger
	protected final static Logger LOGGER =  LoggerFactory
        .getLogger(UtilHttpRequest.class);

	/**
	 * 
	 * HTTP GET request
	 * @param url
	 * @param header
	 * @return
	 */
	public static Map<String, Object> doGet(String url, Header header) {
	    
	    //The response result
	    Map<String, Object> response = new HashMap<String, Object>();
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		
		//If the header parameter is not null,and set the Header
		if (header != null) {getMethod.setRequestHeader(header);}
				
		try {
			client.executeMethod(getMethod);		
			
            //The response be encapsulated to Map type.
            response = getResponseData(getMethod.getStatusCode(), 
                getMethod.getResponseHeaders(), getMethod.getResponseBodyAsString());
            
		} catch (Exception e) {
		    response.put(HttpResponse.STATUS, "The server inner error");
		    response.put("exception",e.getMessage());
			LOGGER.error("HttpServiceSender.doGet() has error:" +url, e.getMessage());
			   
		} finally {
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * HTTP Post request
	 * @param url
	 * @param body
	 * @param header
	 * @return
	 */
	@SuppressWarnings("deprecation")
    public static Map<String, Object> doPost(String url, String body, Header header) {
	    
        Map<String, Object> response = new HashMap<String, Object>();
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
	
		//If the header parameter is not null,and set the Header
		if (header != null) {postMethod.setRequestHeader(header);}
		
		//Set body string
		postMethod.setRequestBody(body);
		
		try {
			client.executeMethod(postMethod);			
	       
            //The response be encapsulated to Map type.
            response = getResponseData(postMethod.getStatusCode(), 
                postMethod.getResponseHeaders(), postMethod.getResponseBodyAsString());
            
		} catch (Exception e) {
		    response.put(HttpResponse.STATUS, "The server inner error");
		    response.put("exception",e.getMessage());
			LOGGER.error("HttpServiceSender.doPost() has error:" + url);
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 
	 * HTTP PUT request
	 * @param url
	 * @param body
	 * @param header
	 * @return
	 */
	@SuppressWarnings("deprecation")
    public static Map<String, Object> doPUT(String url, String body, Header header) {
	    
        Map<String, Object> response = new HashMap<String, Object>();
		HttpClient client = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		
	    //If the header parameter is not null,and set the Header
        if (header != null) { putMethod.setRequestHeader(header); }      
		putMethod.setRequestBody(body);
		try {
			client.executeMethod(putMethod);	
			
			//The response be encapsulated to Map type.
			response = getResponseData(putMethod.getStatusCode(), 
			    putMethod.getResponseHeaders(), putMethod.getResponseBodyAsString());
			    
		} catch (IOException e) {
		    response.put(HttpResponse.STATUS, "The server inner error");
		    response.put("exception",e.getMessage());
			LOGGER.error("HttpServiceSender.doPUT() has error:"+url, e.getMessage());

		} finally {
			putMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 
	 * HTTP DELETE request
	 * @param url
	 * @param header
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String, Object> doDelete(String url, Header header){
	    
	    //result
        Map<String, Object> response = new HashMap<String, Object>();
	   
	    HttpClient client = new HttpClient();
	    DeleteMethod deleteMethod = new DeleteMethod(url);
	    //If the header parameter is not null,and set the Header
        if (header != null) { deleteMethod.setRequestHeader(header); } 
	    try {
            client.executeMethod(deleteMethod);            
           
            //The response be encapsulated to Map type.
            response = getResponseData(deleteMethod.getStatusCode(), 
                deleteMethod.getResponseHeaders(), deleteMethod.getResponseBodyAsString());
            
        } catch (Exception e) {            
            response.put(HttpResponse.STATUS, "The server inner error");
            response.put("exception",e.getMessage());
            LOGGER.error("HttpServiceSender.doDelete() has error::"+url, e.getMessage());   
        } finally {           
            deleteMethod.releaseConnection();            
        }	    
	    return response;
	}
	
	/**
	 * This function encapsulate the data to 
	 * Map Type
	 * @param statusCode
	 * @param header
	 * @param body
	 * @return
	 */
	public static Map<String, Object> getResponseData(int statusCode, Header [] header, String body) {
	    
	    Map<String, Object> data = new HashMap<String, Object>();
        //This status code will be showed to the front-end Page
	    data.put(HttpResponse.STATUS, statusCode);
        //Response header
	    data.put(HttpResponse.HEADERS, header);
        //Response Body
	    data.put(HttpResponse.BODY_STRING, body);
	    
	    return data;
	}
}
