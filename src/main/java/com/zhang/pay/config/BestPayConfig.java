package com.zhang.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 支付配置类
 * 通过Bean注解，当服务启动时自动运行方法。
 * 将各个支付的配置信息写入进去
 */
@Component
public class BestPayConfig {

	@Autowired
	private WxAccountConfig wxAccountConfig;

	@Autowired
	private AlipayAccountConfig alipayAccountConfig;

	/**
	 * 将微信的配置文件当作参数传递进该方法
	 * 方法名称和返回值是固定的
	 * @param wxPayConfig
	 * @return
	 */
	@Bean
	public BestPayService bestPayService(WxPayConfig wxPayConfig) {

		AliPayConfig aliPayConfig = new AliPayConfig();
//		应用的appId
		aliPayConfig.setAppId(alipayAccountConfig.getAppId());
//		商户私钥
		aliPayConfig.setPrivateKey(alipayAccountConfig.getPrivateKey());
//		支付宝公钥
		aliPayConfig.setAliPayPublicKey(alipayAccountConfig.getPublicKey());
//		异步通知地址
		aliPayConfig.setNotifyUrl(alipayAccountConfig.getNotifyUrl());
//		支付完返回的地址
		aliPayConfig.setReturnUrl(alipayAccountConfig.getReturnUrl());
//		创建bestPayService对象
		BestPayServiceImpl bestPayService = new BestPayServiceImpl();
//		设置微信配置
		bestPayService.setWxPayConfig(wxPayConfig);
//		设置Alipay配置
		bestPayService.setAliPayConfig(aliPayConfig);
//		返回BestPayServiceImpl
		return bestPayService;
	}

	@Bean
	public WxPayConfig wxPayConfig() {
		WxPayConfig wxPayConfig = new WxPayConfig();
//		应用的appId
		wxPayConfig.setAppId(wxAccountConfig.getAppId());
//		商户号
		wxPayConfig.setMchId(wxAccountConfig.getMchId());
//		商户密钥
		wxPayConfig.setMchKey(wxAccountConfig.getMchKey());
//		异步通知地址
		wxPayConfig.setNotifyUrl(wxAccountConfig.getNotifyUrl());
//		支付完返回的地址
		wxPayConfig.setReturnUrl(wxAccountConfig.getReturnUrl());
		return wxPayConfig;
	}
}
