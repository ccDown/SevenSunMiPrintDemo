package com.seven.sevensunmiprintdemo.utils;

/**
 * @description 常量类
 * create at 2017-7-17
 */
public class Constants {

    /**应用类型*/
    /**电子钱包应用类型*/
    public static final String APP_CODE_ELECTWALLET = "1";

    /**应用数据类型*/
    public static final String TYPE_ELECTWALLET = "1";//电子钱包
    public static final String TYPE_MOBILEPOINT = "2";//移动积分
    public static final String TYPE_TICKETPAY = "3";//票券支付
    public static final String TYPE_MERCHANTINTEGRAL = "4";//商户积分
    public static final String TYPE_MERCHANTCARD = "5";//商户点卡
    public static final String TYPE_MERCHANTUNION = "6";//商户联盟
    public static final String TYPE_UMPAY = "7";//和包支付
    public static final String TYPE_WATERWAY = "8";//水费缴纳


    /**交易类型*/
    public static final String TRANSACTION_CONSUME = "1";//消费
    public static final String TRANSACTION_CONSUME_CANCEL = "2_1";//消费撤销
    public static final String TRANSACTION_CHARGE_CANCEL = "2_2";//充值撤销
    public static final String TRANSACTION_RETURN = "3";//退货
    public static final String TRANSACTION_CHARGE = "4";//充值(移动积分和联机应用)
    public static final String TRANSACTION_ACCOUNT_CHARGE = "4_1";//账户充值(电子钱包主账户充值)
    public static final String TRANSACTION_CASH_CHARGE = "4_2";//现金充值(电子钱包现金充值)
    public static final String TRANSACTION_ACTUALIZER = "5";//补登


}
