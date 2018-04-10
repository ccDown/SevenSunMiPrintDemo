package com.seven.sevensunmiprintdemo.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @description 卡片信息
 * Created by haidragon on 2017/7/21.
 */

public class CardInfo implements Serializable {
    /**卡号*/
    private String cardno;
    /**姓名*/
    private String name;
    /**性别*/
    private String sex;
    /**出生日期*/
    private String birthdate;
    /**证件类型*/
    private String idtype;
    /**证件号码*/
    private String idnumber;
    /**民族代码*/
    private String nationalcode;
    /**联系电话*/
    private String mobileno;
    /**通讯地址*/
    private String address;
    /**工作单位*/
    private String companyname;
    /**交易记录*/
    private TransactionInfo transactionInfo;

    /**电子钱包业务*/
    /**卡片应用序列号*/
    private String appSerialNumber;
    /**钱包卡号*/
    private String ewalletCardNo;
    /**交易金额*/
    private String ewalletAmount;
    /**钱包余额(交易后余额）*/
    private String ewalletBalance;
    /**有效期*/
    private String expdate;
    /**交易时间*/
    private String datetime;
    /**发卡方标识*/
    private String issuerIdentifier;
    /**脱机交易序号*/
    private String offlineTransactionNo;
    /**交易类型标识*/
    private String ewalletTransactionType;
    /**秘钥版本号*/
    private String keyVersionNo;
    /**秘钥索引号*/
    private String keyIndexNo;
    /**秘钥算法标识*/
    private String algorithmID;
    /**钱包联机交易序号（交易前）*/
    private String onlineTransactionNo;
    /**终端机编号号*/
    private String terminalCode;
    /**终端交易序号（psam卡产生）*/
    private String psamOfflineTransactionNo;
    /**tac*/
    private String tac;
    /**伪随机数*/
    private String prn;
    /**mac1*/
    private String mac1;
    /**电子钱包支付密码是否正确*/
    private boolean isPwd;
    /**交易记录*/
    private ArrayList<String> record;

    public String getAppSerialNumber() {
        return appSerialNumber;
    }

    public void setAppSerialNumber(String appSerialNumber) {
        this.appSerialNumber = appSerialNumber;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getNationalcode() {
        return nationalcode;
    }

    public void setNationalcode(String nationalcode) {
        this.nationalcode = nationalcode;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public String getEwalletCardNo() {
        return ewalletCardNo;
    }

    public void setEwalletCardNo(String ewalletCardNo) {
        this.ewalletCardNo = ewalletCardNo;
    }

    public String getEwalletAmount() {
        return ewalletAmount;
    }

    public void setEwalletAmount(String ewalletAmount) {
        this.ewalletAmount = ewalletAmount;
    }

    public String getEwalletBalance() {
        return ewalletBalance;
    }

    public void setEwalletBalance(String ewalletBalance) {
        this.ewalletBalance = ewalletBalance;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getIssuerIdentifier() {
        return issuerIdentifier;
    }

    public void setIssuerIdentifier(String issuerIdentifier) {
        this.issuerIdentifier = issuerIdentifier;
    }

    public String getOfflineTransactionNo() {
        return offlineTransactionNo;
    }

    public void setOfflineTransactionNo(String offlineTransactionNo) {
        this.offlineTransactionNo = offlineTransactionNo;
    }

    public String getEwalletTransactionType() {
        return ewalletTransactionType;
    }

    public void setEwalletTransactionType(String ewalletTransactionType) {
        this.ewalletTransactionType = ewalletTransactionType;
    }

    public String getKeyVersionNo() {
        return keyVersionNo;
    }

    public void setKeyVersionNo(String keyVersionNo) {
        this.keyVersionNo = keyVersionNo;
    }

    public String getKeyIndexNo() {
        return keyIndexNo;
    }

    public void setKeyIndexNo(String keyIndexNo) {
        this.keyIndexNo = keyIndexNo;
    }

    public String getAlgorithmID() {
        return algorithmID;
    }

    public void setAlgorithmID(String algorithmID) {
        this.algorithmID = algorithmID;
    }

    public String getOnlineTransactionNo() {
        return onlineTransactionNo;
    }

    public void setOnlineTransactionNo(String onlineTransactionNo) {
        this.onlineTransactionNo = onlineTransactionNo;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getPsamOfflineTransactionNo() {
        return psamOfflineTransactionNo;
    }

    public void setPsamOfflineTransactionNo(String psamOfflineTransactionNo) {
        this.psamOfflineTransactionNo = psamOfflineTransactionNo;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getMac1() {
        return mac1;
    }

    public void setMac1(String mac1) {
        this.mac1 = mac1;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ArrayList<String> getRecord() {
        return record;
    }

    public void setRecord(ArrayList<String> record) {
        this.record = record;
    }

    public boolean isPwd() {
        return isPwd;
    }

    public void setPwd(boolean pwd) {
        isPwd = pwd;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "cardno='" + cardno + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", idtype='" + idtype + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", nationalcode='" + nationalcode + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", address='" + address + '\'' +
                ", companyname='" + companyname + '\'' +
                ", transactionInfo=" + transactionInfo +
                ", appSerialNumber='" + appSerialNumber + '\'' +
                ", ewalletCardNo='" + ewalletCardNo + '\'' +
                ", ewalletAmount='" + ewalletAmount + '\'' +
                ", ewalletBalance='" + ewalletBalance + '\'' +
                ", expdate='" + expdate + '\'' +
                ", datetime='" + datetime + '\'' +
                ", issuerIdentifier='" + issuerIdentifier + '\'' +
                ", offlineTransactionNo='" + offlineTransactionNo + '\'' +
                ", ewalletTransactionType='" + ewalletTransactionType + '\'' +
                ", keyVersionNo='" + keyVersionNo + '\'' +
                ", keyIndexNo='" + keyIndexNo + '\'' +
                ", algorithmID='" + algorithmID + '\'' +
                ", onlineTransactionNo='" + onlineTransactionNo + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", psamOfflineTransactionNo='" + psamOfflineTransactionNo + '\'' +
                ", tac='" + tac + '\'' +
                ", prn='" + prn + '\'' +
                ", mac1='" + mac1 + '\'' +
                ", record=" + record +
                '}';
    }
}
