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

import com.httptool.bean.HttpRequestEntity;
import com.httptool.utils.HttpServiceSender;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Ziv
 * @version  [版本号, 2013-10-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HttpToolService {
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String[] doRequest(HttpRequestEntity request){
        
        String[] result = new String[2];
              
        //GET       
        if(request.getMethod().equals("GET")){              
            result = HttpServiceSender.doGet(request.getUrl());
            
        //POST         
        } else if(request.getMethod().equals("POST")){            
            result = HttpServiceSender.doPost(request.getUrl(), 
                request.getBodyString());
      
        //PUT         
        } else if(request.getMethod().equals("PUT")){            
            result = HttpServiceSender.doPUT(request.getUrl(), 
                request.getBodyString());
        
        //Delete    
        } else if(request.getMethod().endsWith("DELETE")){                      
            result = HttpServiceSender.doDelete(request.getUrl());
        }
        
        return result;
    }
    
}
