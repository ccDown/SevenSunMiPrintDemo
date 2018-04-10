package com.seven.sevensunmiprintdemo.bean;

import java.io.Serializable;

/**
 * @description 交易流水信息
 * @author haidragon create at 2017-7-17
 */

public class TransactionInfo implements Serializable{
    public TransactionInfo(){}
    public TransactionInfo(String serialno, String batchno, String merchantname, String merchantno, String terminalno, String operatorno, String mobileno, String type, String voucherno, String referno, String amount, String time, String points, String listno,String appType) {
        this.serialno = serialno;
        this.batchno = batchno;
        this.merchantname = merchantname;
        this.merchantno = merchantno;
        this.terminalno = terminalno;
        this.operatorno = operatorno;
        this.mobileno = mobileno;
        this.type = type;
        this.voucherno = voucherno;
        this.referno = referno;
        this.amount = amount;
        this.time = time;
        this.points = points;
        this.listno = listno;
        this.apptype = appType;
    }

    private String serialno;  //流水号
    private String batchno;  //批次号
    private String originalserialno;  //原流水号(撤销交易)
    private String originalbatchno;  //原批次号(撤销交易)
    private String merchantname;  //商户号
    private String merchantno;  //商户名称
    private String terminalno;  //终端号
    private String operatorno;  //操作号
    private String mobileno;  //手机号
    private String type;  //交易类型
    private String voucherno;  //凭证号
    private String referno;  //参考号
    private String amount;  //交易金额
    private String time;  //交易时间
    private String points;  //交易积分
    private String listno;  //交易笔数序列号
    private String apptype;  //应用类型 1标识电子钱包 2移动积分 3票券支付 4商户积分 5商户点卡 6商户联盟 7和包交易*/
    private CardInfo cardInfo;//卡片信息
    private String tacblock;  //电子钱包tacblock


    public String getListno() {
        return listno;
    }

    public void setListno(String listno) {
        this.listno = listno;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getOriginalserialno() {
        return originalserialno;
    }

    public void setOriginalserialno(String originalserialno) {
        this.originalserialno = originalserialno;
    }

    public String getOriginalbatchno() {
        return originalbatchno;
    }

    public void setOriginalbatchno(String originalbatchno) {
        this.originalbatchno = originalbatchno;
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public String getTerminalno() {
        return terminalno;
    }

    public void setTerminalno(String terminalno) {
        this.terminalno = terminalno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getOperatorno() {
        return operatorno;
    }

    public void setOperatorno(String operatorno) {
        this.operatorno = operatorno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVoucherno() {
        return voucherno;
    }

    public void setVoucherno(String voucherno) {
        this.voucherno = voucherno;
    }

    public String getReferno() {
        return referno;
    }

    public void setReferno(String referno) {
        this.referno = referno;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getTacblock() {
        return tacblock;
    }

    public void setTacblock(String tacblock) {
        this.tacblock = tacblock;
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "serialno='" + serialno + '\'' +
                ", batchno='" + batchno + '\'' +
                ", originalserialno='" + originalserialno + '\'' +
                ", originalbatchno='" + originalbatchno + '\'' +
                ", merchantname='" + merchantname + '\'' +
                ", merchantno='" + merchantno + '\'' +
                ", terminalno='" + terminalno + '\'' +
                ", operatorno='" + operatorno + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", type='" + type + '\'' +
                ", voucherno='" + voucherno + '\'' +
                ", referno='" + referno + '\'' +
                ", amount='" + amount + '\'' +
                ", time='" + time + '\'' +
                ", points='" + points + '\'' +
                ", listno='" + listno + '\'' +
                ", apptype='" + apptype + '\'' +
                ", cardInfo=" + cardInfo +
                ", tacblock='" + tacblock + '\'' +
                '}';
    }

}
