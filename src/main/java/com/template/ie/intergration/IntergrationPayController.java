package com.template.ie.intergration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.template.ie.utils.CustomException;
import com.template.ie.utils.JsonUtil;

/**
 * @function:
 * 		此服务的统一入口
 * 		初步思考是在入口定义一个很简便易用的服务API，简便易用的交易对象(商品对象?)、账户对象，
 * 		数据流转中，一个人的账单里会增加多个商品。
 * 		然后交易的Package里的所有的支付类型各自来实现此API。
 * 		下一步假想一个整体的大型服务，
 * 		然后通过设计模式修整一下内部结构，使之更加合理
 * 		最终是实现一定程度的高聚低耦
 * @logic:
 * 		① 基础校验
 * 		② 公共参数校验
 * 		③ 对外API:
 * 		④ (账户)	-- 开通一个交易账户?(账户增删改查，考虑将账户分离出单独的账户管理服务)
 * 		⑤ (收钱)	-- 在账户存钱、收到money
 * 		⑥ (花钱)	-- 缴费、支付、转账、退费(别管咋说，就是从账户里花钱，money is gone)
 * 		⑦ (账单)	-- 账单记录(账单对象怎么设计呢：1.会计学的账单和报表 2.常见的账单报表)
 * 
 * 		⑧ 对内API:
 * 		⑨ (管理员) -- 权限管理服务
 * @author Clyde 2018-09-26
 * @version 0.0.1
 */
@RestController
@RequestMapping(value="/intergrationPay")
public class IntergrationPayController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @function:
	 * 		设置响应,跨域
	 * @param response
	 */
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setDateHeader("Expires", 0);
	}
	
	/**
	 * @function:
	 * 		此服务入口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
	public String entrance(HttpServletRequest request, HttpServletResponse response) {
		setHeader(response);
		String result = "";
		try {
			String parameter = request.getParameter("parameter");
			logger.debug("接收传入数据：parameter==>{}",parameter);
			if (parameter == null || parameter.equals("")) 	{
				throw new CustomException("1001","parameter必须输入");}
			/*
				这里运行业务处理
			*/
			logger.debug("返回应答数据：result==>{}",result);
		} catch (Exception e) {
			result = JsonUtil.resultJsonString("系统异常 请联系管理员", "XT00");
			logger.error("result==>{},msg==>{}", result, e.getMessage());
		}
		return result;
	}
}