package com.httptool.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.httptool.bean.HttpRequestEntity;
import com.httptool.constant.HttpResponse;
import com.httptool.service.HttpToolService;

/**
 *
 * 
 * @author  Ziv
 * @version  [1.0, 2013-10-21]
 * 
 */
@Controller
public class HttpToolController {
    
    /**
     * 
     * This function is main entrance of the tool.
     * all the Ajax request will be get data by this.
     * @param url
     * @param method
     * @param header
     * @param bodyString
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/doRequest")
    @ResponseBody
    public Map<String, Object> HttpToolMain(HttpServletResponse response,HttpServletRequest request) {
         
        Map<String, Object> data = new HashMap<String, Object>();
        try {          
            //Convert to HttpRequestEntityn
            HttpRequestEntity entity = new HttpRequestEntity();
            entity.setUrl(request.getParameter("url"));
            entity.setMethod(request.getParameter("method"));
            entity.setBody(request.getParameter("body"));
            entity.setHeader(request.getParameter("header"));
            
            //Do request
            HttpToolService httpToolService = new HttpToolService();
            
            //Return json data
            data = httpToolService.doRequest(entity);
            
        } catch(Exception e){
            data.put(HttpResponse.STATUS, "The server inner error");
            data.put("exception",e.getMessage());
            LoggerFactory.getLogger(HttpToolController.class).error("HttpToolController.HttpToolMain()" +
          		"has error", e.getMessage());
        }
        return data;
    }

}
