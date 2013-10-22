package com.httptool.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * HTTP协议请求发送服务
 * 
 * @author Ziv
 * 
 */
public class HttpServiceSender {

	// TODO 把HTTP Service封装起来,而不是用静态方法供外面调用
	protected final static Logger LOGGER =  LoggerFactory
        .getLogger(HttpServiceSender.class);

	/**
	 * 普通GET请求
	 * 
	 * @param url
	 * @return [响应状态头][响应正文]
	 */
	public static String[] doGet(String url) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			client.executeMethod(getMethod);
			if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
				response[0] = String.valueOf(getMethod.getStatusCode());
				response[1] = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOGGER.error("HttpServiceSender.doGet has error:" + e.getMessage());
			LOGGER.error("url is:" + url);
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
	public static String[] doPost(String url, String bodyString) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		postMethod.setRequestHeader("Content-Type", "application/json");
		postMethod.setRequestBody(bodyString);
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {

				response[0] = String.valueOf(postMethod.getStatusCode());
				
				response[1] = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOGGER.error("HttpServiceSender.doPost has error:" + e.getMessage());
			LOGGER.error("url is:" + url + ",body string is:" + bodyString);
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
	public static String[] doPUT(String url, String bodyString) {
		String[] response = new String[2];
		response[0] = "-1";
		HttpClient client = new HttpClient();
		PutMethod putMethod = new PutMethod(url);
		putMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		putMethod.setRequestHeader("Content-Type", "application/json");
		putMethod.setRequestBody(bodyString);
		try {
			client.executeMethod(putMethod);
			if (putMethod.getStatusCode() == HttpStatus.SC_OK) {
				response[0] = String.valueOf(putMethod.getStatusCode());
				response[1] = putMethod.getResponseBodyAsString();
			}
		} catch (IOException e) {
			LOGGER.error("HttpServiceSender.doPUT has error:"
					+ e.getMessage());
			LOGGER.error("url is:" + url +",body string is:" + bodyString);
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
	public static String[] doDelete(String url){
	    
	    //result
	    String[] response = new String[2];
	   
	    HttpClient client = new HttpClient();
	    DeleteMethod deleteMethod = new DeleteMethod(url);
	    
	    try {
            client.executeMethod(deleteMethod);
            
            //get the request result state;
            response[0] = String.valueOf(deleteMethod.getStatusCode());
            
            //request ok 
            if(deleteMethod.getStatusCode() == HttpStatus.SC_OK){
                response[1] = deleteMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {            
            LOGGER.error("HttpServiceSender.doDelete()::"+url, e.getMessage());           
        } finally {           
            deleteMethod.releaseConnection();            
        }	    
	    return response;
	}
}
