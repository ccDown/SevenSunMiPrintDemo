package com.seven.sevensunmiprintdemo.bean;

import com.seven.sevensunmiprintdemo.utils.StringUtil;

import java.util.ArrayList;

import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_BIG;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_MIDDIM;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_SMALL;

/**
 * @author kuan
 * Created on 2018/4/9.
 * @description
 */
public class MobilePrinterTemplet {
    public MobilePrinterTemplet(){}

    public ArrayList<PrintItemObj> printShops(TransactionInfo info, boolean isReport){
        ArrayList arrayList = new ArrayList<PrintItemObj>();
        arrayList.add(new PrintItemObj("     移动消费券",FONT_SIZE_BIG,true));
        arrayList.add(new PrintItemObj("商户存根                          MERCHANT COPY",true));
        arrayList.addAll(printCommon(info));

        arrayList.add(new PrintItemObj("为了您的合法权益，请妥善保留此凭条"));
        arrayList.add(new PrintItemObj(""));
        arrayList.add(new PrintItemObj(""));
        arrayList.add(new PrintItemObj(""));
        arrayList.add(new PrintItemObj(""));

        if (isReport){
            arrayList.add(new PrintItemObj("补打/DUPLICATED", FONT_SIZE_MIDDIM, false));
        }
        return arrayList;

    }

    /**
     * 客户小票打印信息
     */
    public ArrayList<PrintItemObj> printCustomer(TransactionInfo info){
        ArrayList<PrintItemObj> printItemObjs = new ArrayList<PrintItemObj>();

        printItemObjs.add(new PrintItemObj("     移动消费券", FONT_SIZE_BIG, true));

        printItemObjs.add(new PrintItemObj("持卡人存根                     CARDHOLDER COPY", FONT_SIZE_SMALL,true));

        //双方打印相同的地方
        printItemObjs.addAll(printCommon(info));

        printItemObjs.add(new PrintItemObj("为了您的合法权益，请妥善保留此凭条"));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        printItemObjs.add(new PrintItemObj(""));
        return printItemObjs;
    }

    private ArrayList<PrintItemObj> printCommon(TransactionInfo info){
        ArrayList arrayList = new ArrayList<PrintItemObj>();
        arrayList.add(new PrintItemObj("----------------------------------------------"));
        arrayList.add(new PrintItemObj("商户名称(中英文)"));
        arrayList.add(new PrintItemObj("MERCHANT NAME:",true));
        arrayList.add(new PrintItemObj("   "+info.getMerchantname(),FONT_SIZE_MIDDIM,true));
        arrayList.add(new PrintItemObj("商户编号:          "+info.getMerchantno()));
        arrayList.add(new PrintItemObj("MERCHANT NO:",true));
        arrayList.add(new PrintItemObj("终端编号/TERMINAL ID:      操作员号/OPERATOR NO"));
        arrayList.add(new PrintItemObj("    "+info.getTerminalno()+"                  "+info.getOperatorno()));
        arrayList.add(new PrintItemObj("手机号(CELLPHONE NO):"));
        arrayList.add(new PrintItemObj("        "+StringUtil.phoneTrim(info.getMobileno()),FONT_SIZE_MIDDIM,true));
        arrayList.add(new PrintItemObj("----------------------------------------------"));

        arrayList.add(new PrintItemObj("交易类型/TRANS TYPE         凭证号/UOUCHER NO "));
        arrayList.add(new PrintItemObj(StringUtil.getTypeName(info.getType(),info.getApptype())+"        "+info.getReferno(),FONT_SIZE_MIDDIM,true));
        arrayList.add(new PrintItemObj("流水号/TRACE NO             参考号/REFER NO "));
        arrayList.add(new PrintItemObj(info.getSerialno()+"                    "+info.getReferno()));
        arrayList.add(new PrintItemObj("日期/时间(DATA/TIME)        "+StringUtil.formatDatePrint(info.getTime())));
        arrayList.add(new PrintItemObj("金额(AMOUNT):       RMB: "+info.getAmount(),FONT_SIZE_MIDDIM,true));

        if(!StringUtil.isEmpty(info.getPoints())){
            arrayList.add(new PrintItemObj("积分(SCORE):         "+info.getPoints(), FONT_SIZE_MIDDIM));
        }
        arrayList.add(new PrintItemObj("----------------------------------------------"));

        return arrayList;
    }

}
