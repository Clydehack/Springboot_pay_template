package com.template.ie.intergration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.template.ie.utils.ServiceUtil;

/**
 * 测试微信公众号OpenId获取
 */
@RestController
@RequestMapping(value = "/test")
public class TestWxOpenId {

	private Logger logger = LoggerFactory.getLogger(TestWxOpenId.class);
	
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0);
	}
	
	/**
	 * 获取openid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOpenid", method = { RequestMethod.GET, RequestMethod.POST })
	public String getTheCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		setHeader(response);
		String code = request.getParameter("code");// 获取code
		System.out.println(code);
		logger.info("接受页面传来数据", code);
		Map<String, String> params = new HashMap<>();
		params.put("appid", "wx840eb5cb457bf76a");
		params.put("secret", "9c5bc3d2ca286bb5d482508ac1152525");
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		String str = "appid=wx840eb5cb457bf76a&secret=9c5bc3d2ca286bb5d482508ac1152525&grant_type=authorization_code&code="+code;
		
		String result = ServiceUtil.invokeService(str, "getopenid");
		//String result = HttpGetUtil.httpRequestToString("https://api.weixin.qq.com/sns/oauth2/access_token", params);
		System.out.println(result);
		logger.info("微信返回数据", result);
		JSONObject jsonObject = JSONObject.parseObject(result);
		System.out.println(jsonObject);
		String openId = jsonObject.getString("openid");
	    System.out.println("得到的openid为:"+openId);
	    logger.info("得到的openid为", openId);
		return openId;
	}
}