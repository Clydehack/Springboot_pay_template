package com.template.ie.trade.weixinpay.service;

import java.util.HashMap;
import java.util.Map;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.template.ie.trade.weixinpay.sdk.WXPayConfigImpl;

/**
 * @function:
 * 		支付测试类
 * @author Clyde
 * 
 */
public class TestWXPay {

	private WXPay wxpay;
    private WXPayConfigImpl config;
    private String out_trade_no;
    private String total_fee;
    
    public TestWXPay() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
        total_fee = "1";
        // out_trade_no = "201701017496748980290321";
        out_trade_no = "201613091059590000003433-asd002";
    }
    
    /**
     * 	公众号支付  下单
     */
    public void doUnifiedOrder() {
    	System.out.println("公众号支付");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "医院-缴费");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://test.letiantian.me/wxpay/notify");
        data.put("trade_type", "JSAPI");
        data.put("product_id", "12");
        data.put("openid", "12");
        // data.put("time_expire", "20170112104120");

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭订单
     */
    public void doOrderClose() {
        System.out.println("关闭订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.closeOrder(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOrderQuery() {
        System.out.println("查询订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.orderQuery(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOrderReverse() {
        System.out.println("撤销");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.reverse(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 长链接转短链接
     * 测试成功
     */
    public void doShortUrl() {
        String long_url = "weixin://wxpay/bizpayurl?pr=etxB4DY";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("long_url", long_url);
        try {
            Map<String, String> r = wxpay.shortUrl(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退款
     * 已测试
     */
    public void doRefund() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());

        try {
            Map<String, String> r = wxpay.refund(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询退款
     * 已经测试
     */
    public void doRefundQuery() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_refund_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.refundQuery(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对账单下载
     * 已测试
     */
    public void doDownloadBill() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("bill_date", "20161102");
        data.put("bill_type", "ALL");
        try {
            Map<String, String> r = wxpay.downloadBill(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGetSandboxSignKey() throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(data, config.getKey());
        data.put("sign", sign);
        WXPay wxPay = new WXPay(config);
        String result = wxPay.requestWithoutCert("https://api.mch.weixin.qq.com/sandbox/pay/getsignkey", data, 10000, 10000);
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {

        TestWXPay dodo = new TestWXPay();
        /*
        dodo.doOrderQuery();	// 查询订单
        dodo.doOrderReverse();	// 撤销订单
        dodo.doOrderQuery();	// 查询订单
        dodo.doOrderReverse();	// 撤销订单
        dodo.doOrderQuery();	// 查询订单
        */
        dodo.doUnifiedOrder();	// 公众号下单
        dodo.doOrderQuery();	// 查询订单
    }
    
    /**金额单位：分
    
    public String refundRequest(String tradeNo, int refundMoney, int totalMoney) {
    	String responseStr = "退款失败";
    	try {
                WXPayConfigImpl config = WXPayConfigImpl.getInstance();
                WXPay wxpay = new WXPay(config);
                HashMap<String, String> data = new HashMap<>();
                data.put("transaction_id",""+ tradeNo+"");
                data.put("out_refund_no",""+ TimeMillis+"");
                data.put("total_fee",""+totalMoney+"");
                data.put("refund_fee",""+refundMoney+"");
                data.put("refund_fee_type", "CNY");
                data.put("mch_id", config.getMchID());
                Map<String, String> r = wxpay.refund(data);
                String err_code_des = r.get("err_code_des");
                if(err_code_des == null) {
                    responseStr ="退款成功";
                } else {
                    responseStr = err_code_des;
                }
    		} catch (Exception e) {
                responseStr = "系统异常"; 
            }
        	return responseStr;
        }*/
}