package com.template.ie.trade.weixinpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

/**
 * @function: 微信支付配置类
 * @author Clyde
 * 
 */
public class WXPayConfigImpl implements WXPayConfig {

	private String appId = "wx840eb5cb457bf76a";				// 公众号 id
	private String mchId = "1469090602"; 						// 商户号 id
	private String key = "zxQNAHNthFnS1LObiVmuNlb2BpDc16yF"; 	// 密钥key(API密钥)
    private String secret;			// Appsecret
    private String notifyUrl;		// 微信支付结果通知的回调地址:通知url必须为直接可访问的url，不能携带参数。示例：notify_url：“https://pay.weixin.qq.com/wxpay/pay.action”
	private byte[] certData; 		// 证书数据
	private static WXPayConfigImpl INSTANCE;

	private WXPayConfigImpl() throws Exception {
		//String certPath = "src/resources/apiclient_cert.p12";// 证书位置
		String certPath = "/apiclientcert/apiclient_cert.p12";
		File file = new File(certPath);
//		InputStream certStream = new FileInputStream(file);
		InputStream certStream = this.getClass().getResourceAsStream("/apiclientcert/apiclient_cert.p12");
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();
	}

	/**
	 * 获取实例
	 * @return
	 * @throws Exception
	 */
	public static WXPayConfigImpl getInstance() throws Exception {
		if (INSTANCE == null) {
			synchronized (WXPayConfigImpl.class) {	// 加锁
				if (INSTANCE == null) {				// 一次只能有一个线程进入
					INSTANCE = new WXPayConfigImpl();
				}
			}
		}
		return INSTANCE;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppID() {
		return appId;
	}
	
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getMchID() {
		return mchId;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getSecret() {
		return secret;
	}
	
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	
	/**
     * 获取商户证书内容
     *
     * @return 商户证书内容
    public InputStream getCertStream() {
		ByteArrayInputStream certBis;
		certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	} */
	public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }
	
	/**
     * 获取WXPayDomain, 用于多域名容灾自动切换
     *
     * @return
    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    } */

	/**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return int
    public int getHttpConnectTimeoutMs() {
        return 6 * 1000;
    } */
	public int getHttpConnectTimeoutMs() {
		return 2000;
	}

	/**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return int
    public int getHttpReadTimeoutMs() {
        return 8 * 1000;
    } */
	public int getHttpReadTimeoutMs() {
		return 10000;
	}
	
	/**
     * 是否自动上报。
     * 若要关闭自动上报，子类中实现该函数返回 false 即可。
     *
     * @return boolean
     */
    public boolean shouldAutoReport() {
        return true;
    }

    /**
     * 进行健康上报的线程的数量
     *
     * @return int
     */
    public int getReportWorkerNum() {
        return 6;
    }

    /**
     * 健康上报缓存消息的最大数量。会有线程去独立上报
     * 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受
     *
     * @return int
     */
    public int getReportQueueMaxSize() {
        return 10000;
    }

    /**
     * 批量上报，一次最多上报多个数据
     *
     * @return int
     */
    public int getReportBatchSize() {
        return 10;
    }
}