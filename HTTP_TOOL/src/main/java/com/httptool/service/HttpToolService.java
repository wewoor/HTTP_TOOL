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
import com.httptool.utils.UtilHttpRequest;

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
    public String[] doRequest(HttpRequestEntity requestEntity){
        
        String[] result = new String[3];
              
       //GET       
        if(requestEntity.getMethod().equals("GET")){              
            result = UtilHttpRequest.doGet(requestEntity);
            return result;           
        //POST         
        } else if(requestEntity.getMethod().equals("POST")){            
            result = UtilHttpRequest.doPost(requestEntity);
            return result;    
        //PUT         
        } else if(requestEntity.getMethod().equals("PUT")){            
            result = UtilHttpRequest.doPUT(requestEntity);
            return result;     
        //Delete    
        } else if(requestEntity.getMethod().endsWith("DELETE")){                      
            result = UtilHttpRequest.doDelete(requestEntity);
            return result;
        }
        
        return null;
    }
    
}
