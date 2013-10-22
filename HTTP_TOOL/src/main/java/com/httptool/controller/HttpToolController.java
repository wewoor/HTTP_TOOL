package com.httptool.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.httptool.bean.HttpRequestEntity;
import com.httptool.service.HttpToolService;
import com.httptool.utils.JSONUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Ziv
 * @version  [版本号, 2013-10-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
public class HttpToolController {
    
    /**
     * 
     * This function is main entrance of the tool.
     * all the Ajax request will be get data by this. 
     * 
     * @param bodyString
     * @param reqUrl
     * @param header
     * @param response
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(method = RequestMethod.POST, value = "/do")
    public ModelAndView HttpToolMain(@RequestBody String bodyString,
        @PathVariable String method,@PathVariable String header
        ,HttpServletResponse response,HttpServletRequest request){
        
        String[] result = new String[2];
        
        try {
            
            //将参数转换bean
            HttpRequestEntity params = (HttpRequestEntity)JSONUtils.String2Object(bodyString, HttpRequestEntity.class);
            
            //发送请求
            HttpToolService service = new HttpToolService();       
            result = service.doRequest(params);
            
            //return json data
            response.getWriter().print(result);
            
        } catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        return null;
    }

}
