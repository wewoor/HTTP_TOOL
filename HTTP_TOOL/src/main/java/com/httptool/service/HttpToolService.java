/*
 * 文 件 名:  HttpToolService.java
 * 版    权:  Ziv
 * 描    述:  <描述>
 * 修 改 人:  Ziv
 * 修改时间:  2013-10-21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.httptool.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;

import com.httptool.bean.HttpRequestEntity;
import com.httptool.utils.UtilHttpRequest;

/**
 * The Http request service
 * @author  Ziv
 * @version  [v1.0, 2013-10-21]
 */
public class HttpToolService {
    
    /**
     * 
     * Send http request service
     * @param requestEntity
     * @return
     */
    public Map<String, Object> doRequest(HttpRequestEntity requestEntity) {
        
        Map<String, Object> result = new HashMap<String, Object>();
        Header header = null;
        
        if (!(requestEntity.getHeader().trim().equals(""))) {
            //Analyse the header String
            header = new Header();
            String [] str = requestEntity.getHeader().trim().split(";");
            for(int i = 0 ; i < str.length ; i ++) {
                String [] temp = str[i].split("=");
                header.setName(temp[0]);
                header.setValue(temp[1]);
            } 
        }
        
        if (requestEntity.getMethod().equals("GET")) {//GET               
            result = UtilHttpRequest.doGet(requestEntity.getUrl(), header);
            return result;           
              
        } else if (requestEntity.getMethod().equals("POST")) {//POST               
            result = UtilHttpRequest.doPost(requestEntity.getUrl(), requestEntity.getBody().trim(), 
                header);
            return result;    
        
        } else if (requestEntity.getMethod().equals("PUT")) {//PUT                   
            result = UtilHttpRequest.doPUT(requestEntity.getUrl(), requestEntity.getBody().trim(), 
                header);
            return result;     
        
        } else if (requestEntity.getMethod().endsWith("DELETE")) {//Delete                         
            result = UtilHttpRequest.doDelete(requestEntity.getUrl(), header);
            return result;
        }
        
        return null;
    }
    
}
