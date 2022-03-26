package com.zhang.pay.enums;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import lombok.Getter;


@Getter
public enum PayPlatformEnum {

	//1-支付宝,2-微信
	ALIPAY(1),

	WX(2),
	;

	Integer code;

	PayPlatformEnum(Integer code) {
		this.code = code;
	}

	/**
	 * 遍历类中的所有枚举，当和参数中的皮配置，返回当前枚举
	 * @param bestPayTypeEnum
	 * @return
	 */
	public static PayPlatformEnum getByBestPayTypeEnum(BestPayTypeEnum bestPayTypeEnum) {
		for (PayPlatformEnum payPlatformEnum : PayPlatformEnum.values()) {
			if (bestPayTypeEnum.getPlatform().name().equals(payPlatformEnum.name())) {
				return payPlatformEnum;
			}
		}
		throw new RuntimeException("错误的支付平台: " + bestPayTypeEnum.name());
	}
}
