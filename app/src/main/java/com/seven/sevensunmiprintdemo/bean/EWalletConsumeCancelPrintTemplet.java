package com.seven.sevensunmiprintdemo.bean;


import com.seven.sevensunmiprintdemo.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_BIG;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_MIDDIM;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_SMALL;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_ELECTWALLET;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_MERCHANTCARD;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_MERCHANTINTEGRAL;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_MERCHANTUNION;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_MOBILEPOINT;
import static com.seven.sevensunmiprintdemo.utils.StringUtil.TYPE_TICKETPAY;

/**
 *
 * @description 电子钱包消费撤销打印小票模板
 * Created by kuan on 2017/FONT_SIZE_MIDDIM/29.
 */

public class EWalletConsumeCancelPrintTemplet {

    public List<PrintItemObj> printEWallet(TransactionInfo info, boolean isRePrint){
        ArrayList<PrintItemObj> printItemObjs = new ArrayList<PrintItemObj>();

        printItemObjs.add(new PrintItemObj("   居民卡"+getTitle(info.getApptype()), FONT_SIZE_BIG, true));
        printItemObjs.add(new PrintItemObj("商户存根                       MERCHANT COPY", FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("----------------------------------------------"));
        printItemObjs.add(new PrintItemObj("商户名称(中英文)", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("MERCHANT NAME:", FONT_SIZE_SMALL, true));
        printItemObjs.add(new PrintItemObj(info.getMerchantname(), FONT_SIZE_MIDDIM, true));

        printItemObjs.add(new PrintItemObj("商户编号:            "+info.getMerchantno(), FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("MERCHANT NO:", FONT_SIZE_SMALL, true));

        printItemObjs.add(new PrintItemObj("终端编号/TERMINAL ID:    操作员号/OPERATOR NO", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(info.getTerminalno()+"                    "+info.getOperatorno(), FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("卡号(CARD NO):", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("        "+StringUtil.phoneTrim(info.getMobileno()), FONT_SIZE_MIDDIM, true));
        printItemObjs.add(new PrintItemObj("----------------------------------------------"));

        printItemObjs.add(new PrintItemObj("发卡行号/ISS NO              收单行号/ACQ NO", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("0490031300010012"+"          "+"0000532001", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("交易类型/TRANS TYPE", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(StringUtil.getTypeName(info.getType(),info.getApptype()), FONT_SIZE_MIDDIM, false));

        printItemObjs.add(new PrintItemObj("批次号/BATCH No             凭证号/UOUCHER NO", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(info.getBatchno()+"                             "+info.getVoucherno(), FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("流水号/TRACE NO", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(info.getSerialno(), FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("日期/时间(DATA/TIME)", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("        "+StringUtil.formatDatePrint(info.getTime()), FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(getPointOrCards(info.getApptype()), FONT_SIZE_MIDDIM, false));
        printItemObjs.add(new PrintItemObj("         -"+StringUtil.formatMoney(info.getAmount()), FONT_SIZE_MIDDIM, false));
        printItemObjs.add(new PrintItemObj("----------------------------------------------"));

        printItemObjs.add(new PrintItemObj("备注（REFERNCE）", FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("原批次号："+StringUtil.fillLeft(info.getOriginalbatchno(),"0",6), FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("原流水号："+StringUtil.fillLeft(info.getOriginalserialno(),"0",6), FONT_SIZE_SMALL, false));

        printItemObjs.add(new PrintItemObj("----------------------------------------------"));

        printItemObjs.add(new PrintItemObj("持卡人签名(CARDHOLDER SIGNATURE):", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj("----------------------------------------------"));
        printItemObjs.add(new PrintItemObj("本人确认以上交易同意将其计入本卡账户", FONT_SIZE_SMALL, false));
        printItemObjs.add(new PrintItemObj("----------------------------------------------"));
        printItemObjs.add(new PrintItemObj("为了您的合法权益，请妥善保留此凭条", FONT_SIZE_SMALL, false));

        if (isRePrint){
            printItemObjs.add(new PrintItemObj("重打印", FONT_SIZE_MIDDIM, false));
        }

        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        return printItemObjs;
    }

    /**
     * 小票打印标题
     * @param apptype
     * @return
     */
    private String getTitle(String apptype){
        String appname = "";
        switch (apptype){
            case TYPE_ELECTWALLET:
                appname = "电子钱包";
                break;
            case TYPE_MOBILEPOINT:
                appname = "移动积分";
                break;
            case TYPE_TICKETPAY:
                appname = "票券支付";
                break;
            case TYPE_MERCHANTINTEGRAL:
                appname = "商户积分";
                break;
            case TYPE_MERCHANTCARD:
                appname = "商户点卡";
                break;
            case TYPE_MERCHANTUNION:
                appname = "商户联盟";
                break;
            default:
                break;
        }
        return appname;
    }

    /**
     * 小票打印消费种类
     * @param apptype
     * @return
     */
    private String getPointOrCards(String apptype){
        String appname = "";
        switch (apptype){
            case TYPE_ELECTWALLET:
                appname = "金额(AMOUNT):";
                break;
            case TYPE_MOBILEPOINT:
                appname = "积分(SCORE):";
                break;
            case TYPE_TICKETPAY:
                appname = "积分(SCORE):";
                break;
            case TYPE_MERCHANTINTEGRAL:
                appname = "积分(SCORE):";
                break;
            case TYPE_MERCHANTCARD:
                appname = "点数（AMOUNT)";
                break;
            case TYPE_MERCHANTUNION:
                appname = "积分(SCORE):";
                break;
            default:
                break;
        }
        return appname;
    }
}
