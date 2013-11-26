package com.httptool.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httptool.bean.HttpRequestEntity;



/**
 * 
 * HTTP协议请求发送服务
 * 
 * @author Ziv
 * 
 */
public class UtilHttpRequest {

	// TODO 把HTTP Service封装起来,而不是用静态方法供外面调用
	protected final static Logger LOGGER =  LoggerFactory
        .getLogger(UtilHttpRequest.class);

	/**
	 * 普通GET请求
	 * 
	 * @param url
	 * @return [响应状态头][响应正文]
	 */
	public static String[] doGet(HttpRequestEntity requestEntity) {
	    
	    //the response result
		String[] response = new String[3];	
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(requestEntity.getUrl());
		
	    //If the header parameter is not null,and set the Header
        if(requestEntity.getHeader() != null) {
            getMethod.setRequestHeader(requestEntity.getHeader());
        //the below is default set for header
        } else {            
            getMethod.setRequestHeader("Content-Type", "application/json");
        }
		
		try {
			client.executeMethod(getMethod);		
			//save the response status,
			//this status code will be showed to the front-end Page
			response[0] = String.valueOf(getMethod.getStatusCode());			
			//response header
			response[2] = getMethod.getResponseHeaders().toString();
			if (getMethod.getStatusCode() == HttpStatus.SC_OK) {		    
			    //if the request ok,get the response body
				response[1] = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOGGER.error("HttpServiceSender.doGet() has error:" +requestEntity, e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 普通POST请求
	 * 
	 * @param url
	 *            请求url
	 * @param bodyString
	 *            提交参数
	 * @return [响应状态头][响应正文]
	 */
	@SuppressWarnings("deprecation")
    public static String[] doPost(HttpRequestEntity requestEntity) {
	    
		String[] response = new String[3];
		response[0] = "";
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(requestEntity.getUrl());
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		
		//If the header parameter is not null,and set the Header
		if(requestEntity.getHeader() != null) {
		    postMethod.setRequestHeader(requestEntity.getHeader());
		//the below is default set for header
		} else {		    
		    postMethod.setRequestHeader("Content-Type", "application/json");
		}
		
		//set body string
		postMethod.setRequestBody(requestEntity.getBodyString());
		
		try {
			client.executeMethod(postMethod);			
	        //save the response status,
            //this status code will be showed to the front-end Page
            response[0] = String.valueOf(postMethod.getStatusCode());
            //response header
            response[2] = postMethod.getResponseHeaders().toString();
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {			        
				response[1] = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOGGER.error("HttpServiceSender.doPost() has error:" + requestEntity);
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 普通GET请求
	 * 
	 * @param url
	 * @return [响应状态头][响应正文]
	 */
	@SuppressWarnings("deprecation")
    public static String[] doPUT(HttpRequestEntity requestEntity) {
	    
		String[] response = new String[3];
		response[0] = "";
		HttpClient client = new HttpClient();
		PutMethod putMethod = new PutMethod(requestEntity.getUrl());
		putMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		
	    //If the header parameter is not null,and set the Header
        if(requestEntity.getHeader() != null) {
            putMethod.setRequestHeader(requestEntity.getHeader());            
        //the below is default set for header
        } else {            
            putMethod.setRequestHeader("Content-Type", "application/json");
        }       
		putMethod.setRequestBody(requestEntity.getBodyString());
		try {
			client.executeMethod(putMethod);
	        //save the response status,
            //this status code will be showed to the front-end Page
            response[0] = String.valueOf(putMethod.getStatusCode());
            //response header
            response[2] = putMethod.getResponseHeaders().toString();
			if (putMethod.getStatusCode() == HttpStatus.SC_OK) {
				response[1] = putMethod.getResponseBodyAsString();
			}
		} catch (IOException e) {
			LOGGER.error("HttpServiceSender.doPUT() has error:"+requestEntity, e.getMessage());
		} finally {
			putMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 
	 * delete request
	 * @param url
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] doDelete(HttpRequestEntity requestEntity){
	    
	    //result
	    String[] response = new String[3];
	   
	    HttpClient client = new HttpClient();
	    DeleteMethod deleteMethod = new DeleteMethod(requestEntity.getUrl());
	    //If the header parameter is not null,and set the Header
        if(requestEntity.getHeader() != null) {
            deleteMethod.setRequestHeader(requestEntity.getHeader());
        //the below is default set for header
        } else {            
            deleteMethod.setRequestHeader("Content-Type", "application/json");
        }
	    try {
            client.executeMethod(deleteMethod);            
            //get the request result state;
            response[0] = String.valueOf(deleteMethod.getStatusCode());    
            //response header
            response[2] = deleteMethod.getResponseHeaders().toString();
            //request ok 
            if(deleteMethod.getStatusCode() == HttpStatus.SC_OK){
                response[1] = deleteMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {            
            LOGGER.error("HttpServiceSender.doDelete() has error::"+requestEntity, e.getMessage());           
        } finally {           
            deleteMethod.releaseConnection();            
        }	    
	    return response;
	}
}
