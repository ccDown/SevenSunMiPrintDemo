package com.seven.sevensunmiprintdemo.bean;


import com.seven.sevensunmiprintdemo.utils.Constants;
import com.seven.sevensunmiprintdemo.utils.DoubleUtil;
import com.seven.sevensunmiprintdemo.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_MIDDIM;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_MIDSMALL;
import static com.seven.sevensunmiprintdemo.utils.AidlUtil.FONT_SIZE_SMALL;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_ACCOUNT_CHARGE;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_ACTUALIZER;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_CASH_CHARGE;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_CHARGE;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_CHARGE_CANCEL;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_CONSUME;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_CONSUME_CANCEL;
import static com.seven.sevensunmiprintdemo.utils.Constants.TRANSACTION_RETURN;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_ELECTWALLET;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_MERCHANTCARD;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_MERCHANTINTEGRAL;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_MERCHANTUNION;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_MOBILEPOINT;
import static com.seven.sevensunmiprintdemo.utils.Constants.TYPE_TICKETPAY;

/**
 * @author 宽
 * Created on 2017/7/19.
 * @descrixption 结算小票模板
 */

public class ClearPrinterTemplet {

    private List<TransactionInfo>[] infos;//当前应用类型全部的数据
    private int hasContent = 0;//哪个list中含有数据
    private SettleInfo[] settleInfo = new SettleInfo[6];//保存交易汇总数据

    public ClearPrinterTemplet(List<TransactionInfo>[] infos) {
        this.infos = infos;

        for (int i = 0; i < 6; i++) {
            settleInfo[i] = new SettleInfo();
        }

        for (int i = 0; i < infos.length; i++) {
            if (null != infos[i] && infos[i].size() > 0) {
                hasContent = i;
            }
            for (TransactionInfo transactionInfo : infos[i]) {
                getSettleInfo(transactionInfo);
            }
            i++;
        }
    }

    /**
     * 总计单打印信息
     */
    public ArrayList<PrintItemObj> printSettReport() {
        ArrayList<PrintItemObj> list = new ArrayList<PrintItemObj>();

        list.add(new PrintItemObj("          "+getTitle(infos[0].get(0).getApptype()), FONT_SIZE_MIDDIM, true));
        list.add(new PrintItemObj("----------------------------------------------"));
        list.addAll(printClearCommon());
        list.add(new PrintItemObj("----------------------------------------------"));
        list.add(new PrintItemObj("结算总计单/SETT REPORT", FONT_SIZE_MIDDIM, true));
        list.add(new PrintItemObj("=============================================="));
        list.add(new PrintItemObj("类型/TYPE  笔数/SUM  " +
                ((infos[0].get(0).getApptype().equals(Constants.TYPE_ELECTWALLET) ? "AMOUNT" :
                        (infos[0].get(0).getApptype().equals(Constants.TYPE_TICKETPAY) ? "AMOUNT" : "POINTS")))
                , FONT_SIZE_SMALL, false));

        list.add(new PrintItemObj("----------------------------------------------"));

        for (SettleInfo info : settleInfo) {
            if (null != info.getType()) {
                switch (info.getType()) {
                    case "商户积分消费":
                    case "移动积分消费":
                    case "商户点卡消费":
                    case "商户点卡消费撤销":
                    case "商户点卡充值":
                    case "商户点卡充值撤销":
                        list.add(new PrintItemObj(info.getType() + "     " + info.getSum() + "       " + (int) info.getMoney(), FONT_SIZE_MIDSMALL, false));
                        break;
                    default:
                        list.add(new PrintItemObj(info.getType() + " " + info.getSum() + "       " + StringUtil.formatMoney(String.valueOf(info.getMoney())), FONT_SIZE_MIDSMALL, false));
                        break;
                }
            }
        }

        list.add(new PrintItemObj("=============================================="));
        list.add(new PrintItemObj(""));
        list.add(new PrintItemObj(""));
        list.add(new PrintItemObj(""));
        list.add(new PrintItemObj(""));
        return list;
    }

    /**
     * 成功交易明细打印信息
     */
    public ArrayList<PrintItemObj> printTransactionList() {
        ArrayList<PrintItemObj> list = new ArrayList<PrintItemObj>();

        list.add(new PrintItemObj("     成功交易明细/SUCC LIST", FONT_SIZE_MIDDIM, true));
        list.add(new PrintItemObj("=============================================="));
        list.addAll(printClearCommon());
        list.add(new PrintItemObj("=============================================="));
        list.add(new PrintItemObj("序号  流水号   参考号   类型", FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj(" NO   TRACE    REF.NO    TYPE", FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("卡号               " +
                (infos[0].get(0).getApptype().equals(Constants.TYPE_ELECTWALLET) ? "金额" : (infos[0].get(0).getApptype().equals(Constants.TYPE_TICKETPAY) ? "金额" : "积分/点数"))
                , FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("CARD               " + (infos[0].get(0).getApptype().equals(Constants.TYPE_ELECTWALLET) ? "AMOUNT" : (infos[0].get(0).getApptype().equals(Constants.TYPE_TICKETPAY) ? "AMOUNT" : "POINTS"))
                , FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("----------------------------------------------"));
        for (int j = 0; j < infos.length; j++) {
            for (int i = 0; null != infos[j] && i < infos[j].size(); i++) {
                list.add(new PrintItemObj(StringUtil.fillLeft(i + 1 + "", "0", 3) + " " + infos[j].get(i).getSerialno() + " " + infos[j].get(i).getReferno() + " " + StringUtil.getTypeName(infos[j].get(i).getType(), infos[j].get(i).getApptype()), FONT_SIZE_MIDSMALL, false));
                list.add(new PrintItemObj(infos[j].get(i).getMobileno() + "     " +
                        (infos[j].get(i).getApptype().equals(Constants.TYPE_ELECTWALLET) ? StringUtil.formatMoney(infos[j].get(i).getAmount()) : StringUtil.formatMoney(infos[j].get(i).getPoints()))
                        , FONT_SIZE_MIDSMALL, false));

//                //判断交易类型
//                //电子钱包  获取money字段
//                //商户积分  获取int处理的points字段
//                list.add(new PrintItemObj(infos[j].get(i).getMobileno() + "         " +
//                        (infos[j].get(i).getApptype().equals(Constants.TYPE_ELECTWALLET) ?
//                                infos[j].get(i).getAmount() : (infos[j].get(i).getApptype().equals(Constants.TYPE_MERCHANTINTEGRAL)?
//                                Integer.parseInt(infos[j].get(i).getPoints()):infos[j].get(i).getPoints()))
//                        , FONT_SIZE_MIDSMALL, false));
            }
        }
        list.add(new PrintItemObj("=============================================="));
        list.add(new PrintItemObj(""));
        list.add(new PrintItemObj(""));
        list.add(new PrintItemObj(""));
        return list;
    }

    /**
     * 交易汇总和明细相同的信息
     *
     * @return
     */
    private List<PrintItemObj> printClearCommon() {
        List<PrintItemObj> list = new ArrayList<>();
        int length = infos[hasContent].size() - 1;
        list.add(new PrintItemObj("商户名称(中英文)", FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("MERCHANT NAME:", FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("  " + infos[hasContent].get(length).getMerchantname(), FONT_SIZE_MIDDIM, true));
        list.add(new PrintItemObj("商户编号: " + infos[hasContent].get(length).getMerchantno(), FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("MERCHANT NO: ", FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("终端编号/TERMINAL ID:" + infos[hasContent].get(length).getTerminalno(), FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("操作员号/OPERATOR NO:  " + infos[hasContent].get(length).getOperatorno(), FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("批次号(BATCH NO):   " + infos[hasContent].get(length).getBatchno(), FONT_SIZE_MIDSMALL, false));
        list.add(new PrintItemObj("日期/时间(DATA/TIME):" + StringUtil.formatDatePrint(infos[hasContent].get(length).getTime()), FONT_SIZE_MIDSMALL, false));
        return list;
    }

    /**
     * 打印结算汇总模板
     */
    private class SettleInfo {
        private int sum;
        private double money;
        private String type;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public double getMoney() {
            return money;
        }

        public void setPoints(double money) {
            this.money = money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * 根据消费类型组装打印小票汇总信息
     *
     * @param info
     */
    private void getSettleInfo(TransactionInfo info) {


        String transcationMoney = null;

        switch (info.getApptype()) {
            case Constants.APP_CODE_ELECTWALLET:
                transcationMoney = info.getAmount();
                break;
            default:
                transcationMoney = info.getPoints();
                break;
        }

        switch (info.getType()) {
            case TRANSACTION_CONSUME:
                setSettleInfos(info,0,transcationMoney);
                break;
            case TRANSACTION_CONSUME_CANCEL:
                setSettleInfos(info,1,transcationMoney);
                break;
            case TRANSACTION_CHARGE_CANCEL:
                setSettleInfos(info,2,transcationMoney);
                break;
            case TRANSACTION_RETURN:
                setSettleInfos(info,3,transcationMoney);
                break;
            case TRANSACTION_CHARGE:
            case TRANSACTION_ACCOUNT_CHARGE:
            case TRANSACTION_CASH_CHARGE:
                setSettleInfos(info,4,transcationMoney);

                break;
            case TRANSACTION_ACTUALIZER:
                setSettleInfos(info,5,transcationMoney);
                break;
            default:
                break;
        }
    }

    private void setSettleInfos(TransactionInfo info,int i,String transcationMoney){
        settleInfo[i].setType(StringUtil.getTypeName(info.getType(), info.getApptype()));
        settleInfo[i].setPoints(DoubleUtil.sum(settleInfo[i].getMoney(), Double.parseDouble(transcationMoney)));
        settleInfo[i].setSum(settleInfo[i].getSum() + 1);
    }
    /**
     * 小票打印标题
     *
     * @param apptype
     * @return
     */
    private String getTitle(String apptype) {
        String appname = "";
        switch (apptype) {
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

}
