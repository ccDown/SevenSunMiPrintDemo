package com.seven.sevensunmiprintdemo.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Create by haidragon on 2017-7-19
 *
 * @description 字符串处理工具类
 */
public class StringUtil {
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * 正则：URL
     */
    private static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";

    /**
     * 正则：IP
     */
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileNum(CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 验证URL
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isURL(CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * 验证IP
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isIP(CharSequence input) {
        return isMatch(REGEX_IP, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 判断是否是正确的金额格式
     *
     * @param money
     * @return
     */
    public static boolean isNumber(String money) {
        if (!isEmpty(money) && !money.startsWith(".")) {
            Pattern p = Pattern.compile("^(\\d+)(\\.\\d{0,3})?.*$");
            Matcher m = p.matcher(money);
            return m.matches();
        }
        return false;
    }

    /**
     * 匹配是否是全数字
     *
     * @param num
     * @return
     */
    public static boolean isNumerical(String num) {
        if (!isEmpty(num)) {
            Pattern p = Pattern.compile("^\\d+$");
            Matcher m = p.matcher(num);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断字符串中是否包数字
     *
     * @param num
     * @return
     */
    public static boolean isContainNumerical(String num) {
        if (!isEmpty(num)) {
            Pattern p = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9]).*$");
            Matcher m = p.matcher(num);
            return m.matches();
        }
        return false;
    }

    /**
     * 匹配小数点后两位(0.05)
     *
     * @param str
     * @return
     */
    public static boolean isBigDecimal(String str) {
        Matcher match = null;
        if (isNumeric(str) == true) {
            Pattern pattern = Pattern.compile("[0-9]*");
            match = pattern.matcher(str.trim());
        } else {
            if (str.trim().indexOf(".") != -1) {
                Pattern pattern = Pattern.compile("^[0-9]+\\.[1-9]|[0-9]+\\.[0-9][0-9]$");
                match = pattern.matcher(str.trim());
            }
        }
        return match.matches();
    }

    public static boolean is2Decimal(String str) {
        Matcher match = null;
        if (isNumeric(str) == true) {
            Pattern pattern = Pattern.compile("[0-9]*");
            match = pattern.matcher(str.trim());
        } else {
            if (str.trim().indexOf(".") != -1) {
                Pattern pattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
                match = pattern.matcher(str.trim());
            }
        }
        return match.matches();
    }

    /**
     * 判断是否是纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是纯字母
     *
     * @param str
     * @return
     */
    public static boolean isLetters(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断用户名格式是否正确
     *
     * @param userName
     * @return
     */
    public static boolean isUserName(String userName) {
        String str = "^[A-Za-z0-9\\u4e00-\\u9fa5]\\w{2,19}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(userName);
        return m.matches();
    }

    /**
     * 获得当前日期和时间
     *
     * @return 2014-07-22 10:59:05
     */
    public static String getCurrentDateAndTime() {
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    // 获取当期时间格式为： yyyymmddhhmmss
    public static String getDate() {
        return getCurrentTime("yyyyMMddHHmmss");
    }

    // 获取当期时间格式为： mmddhhmmss
    public static String getDateTime() {
        return getCurrentTime("MMddHHmmss");
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String getOrderid() {
        return getCurrentTime("yyyyMMddHHmmss") + Math.round(Math.random() * 9000 + 1000);
    }

    public static String genOutTradNo() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获得当前日期(年)
     *
     * @return 2014
     */
    public static String getCurrentYear() {
        return getCurrentTime("yyyy");
    }

    /**
     * 获得当前日期(月)
     *
     * @return 2014-12
     */
    public static String getCurrentMonth() {
        return getCurrentTime("yyyy-MM");
    }

    /**
     * 获得当前日期(月)
     *
     * @return 2014-12-24
     */
    public static String getCurrentDay() {
        return getCurrentTime("yyyy-MM-dd");
    }

    /**
     * 截取卡号 123456*****8888
     *
     * @param cardNo
     * @return
     */
    public static String trimCardNo(String cardNo) {
        if (cardNo != null && !"".equals(cardNo)) {
            if (cardNo.length() > 9) {
                String header = cardNo.substring(0, 6);
                String tail = cardNo.substring(cardNo.length() - 4, cardNo.length());
                int len = cardNo.length() - (header.length() + header.length());
                StringBuilder sb = new StringBuilder();
                sb.append(header);
                for (int i = 0; i <= len; i++) {
                    sb.append("*");
                }
                sb.append(tail);
                return sb.toString();
            }
        }
        return null;
    }

    /**
     * 截身份证号 1**************8
     *
     * @param IdNo
     * @return
     */
    public static String trimIdNo(String IdNo) {
        if (IdNo != null && !"".equals(IdNo)) {
            if (IdNo.length() > 9) {
                String header = IdNo.substring(0, 1);
                String tail = IdNo.substring(IdNo.length() - 1, IdNo.length());
                int len = IdNo.length() - (header.length() + header.length());
                StringBuilder sb = new StringBuilder();
                sb.append(header);
                for (int i = 0; i <= len; i++) {
                    sb.append("*");
                }
                sb.append(tail);
                return sb.toString();
            }
        }
        return null;
    }

    /**
     * 截取卡号 后4位
     *
     * @param cardNo
     * @return
     */
    public static String trimCardNoBehind4(String cardNo) {
        if (cardNo != null && !"".equals(cardNo)) {
            String tail = cardNo.substring(cardNo.length() - 4, cardNo.length());
            return tail;
        }
        return null;
    }

    /**
     * 格式化金额(例如：500.00)
     *
     * @param money 金额
     * @return 如果返回null，金额格式错误
     */
    public static String formatMoney(String money) {
        String result = money;
        try {
            if (isEmpty(money) || "0".equals(money) || "0.0".equals(money)) {
                return "0.00";
            }
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##,##0.00");
            return myformat.format(Double.parseDouble(money));
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 格式化金额(例如：500.00)
     *
     * @param money 金额
     * @return 如果返回null，金额格式错误
     */
    public static String formartNumber(String money) {
        String result = money;
        try {
            if (isEmpty(money) || "0".equals(money) || "0.00".equals(money)) {
                return "0.0";
            }
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##,##0.0");
            return myformat.format(Double.parseDouble(money));
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * 把字符串转换成html标签
     *
     * @param context
     * @param resStrID
     * @param text
     * @return
     */
    public static Spanned str2html(Context context, int resStrID, String text) {
        String str = String.format(context.getResources().getString(resStrID), text);
        return Html.fromHtml(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 转换大金额单位
     *
     * @param money 金额
     * @param unit  换算单位
     * @return
     */
    public static String convertMoneyUnit(double money, int unit) {
        String unitStr = null;
        if (money > 0 && unit >= 1) {
            if (money < 1000) {
                unitStr = String.valueOf(money + "千");
            } else if (money < 10 * 1000) {
                unitStr = String.valueOf(money + "万");
            } else if (money < 100 * 10000) {
                unitStr = String.valueOf(money + "千万");
            } else if (money < 10000 * 100000) {
                unitStr = String.valueOf(money + "千万");
            }
        }
        return unitStr;
    }

    /**
     * 格式化文件大小
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小�?
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小�?
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五�?
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小�?
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小�?
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五�?
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小�?
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    /**
     * 截取字符串（手机号，银行卡，邮箱），省略部分用 * 代替
     *
     * @param number
     * @return
     */
    public static String payinfoTrim(String number) {
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean isNumber = pattern.matcher(number).matches();
        if (isNumber) {// 判断是否为电话号或者银行卡
            if (number.length() >= 16) {// 判断是否为银行卡
                return bankcardTrim(number);
            } else {
                return phoneTrim(number);
            }
        } else {
            if (isEmail(number)) {// 判断是否为邮箱格式
                return emailTrim(number);
            } else {
                return number;
            }
        }
    }

    /**
     * 截取电话号码，如：156****4119
     *
     * @param phone 电话号码
     * @return
     */
    public static String phoneTrim(String phone) {
        if (!isEmpty(phone) && phone.length() >= 11) {
            return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
        }
        return phone;
    }

    /**
     * 截银行卡号码，如：201304********3445
     *
     * @param bankcard
     * @return
     */
    public static String bankcardTrim(String bankcard) {
        int length = bankcard.length();
        if (length >= 16) {
            return bankcard.subSequence(0, 6) + "********" + bankcard.substring(length - 4, length);
        }
        return bankcard;
    }

    /**
     * 截取邮箱，如：lix****@163.com
     *
     * @param email 邮箱号
     * @return
     */
    public static String emailTrim(String email) {
        if (!isEmpty(email)) {
            int lastIndex = email.lastIndexOf("@");
            String emailSub = email.substring(0, lastIndex);
            if (emailSub.length() > 4) {
                return email.replace(email.substring(lastIndex - 4, lastIndex), "****");
            } else {
                return email.replace(emailSub, "****");
            }
        }
        return email;
    }

    /**
     * 时间字符串转换格式（yyyy年MM月dd日）
     *
     * @param str1
     * @return
     */
    public static String dateTransfer(String str1) {
        String newD = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = format.parse(str1);
            format = new SimpleDateFormat("yyyy年MM月dd日");
            newD = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newD;
    }

    /**
     * 获取当前时间
     */
    public static String getYearMonthDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 校验护照
     */
    public static boolean checkoutPassport(String passport) {
        Pattern pattern = Pattern.compile("[a-zA-Z][0-9]{7,8}");
        boolean isPassport = pattern.matcher(passport).matches();
        return isPassport;
    }

    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    // 获取手机系统版本
    public static String getLocalSystemVersion() {
        String version = android.os.Build.VERSION.RELEASE;
        if (version == null) {
            version = "";
        }
        return version;
    }

    /**
     * 十六进制的ASCII码转字符串
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex){
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    /**
     * 字节数组转十六进制字符串
     * */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static String hexStr =  "0123456789ABCDEF";
    public static byte[] hexStringToBinary(String hexString){
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;
        byte low = 0;

        for(int i=0;i<len;i++){
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);
        }
        return bytes;
    }




    /**左补指定元素*/
    public static String fillLeft(String value, String element, int count){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<count - value.length();i++){
            sb.append(element);
        }
        sb.append(value);
        return sb.toString();
    }

    //交易金额处理
    public static String amount(String tradeAmount) {
        String rs = "";
        int a = 0;
        if (tradeAmount == null || "".equals(tradeAmount)) {
            return rs;
        }
        try {
            Integer.parseInt(tradeAmount);
        } catch (NumberFormatException e) {
            return rs;
        }
        if (tradeAmount.startsWith("0")) {
            return rs;
        }
        a = tradeAmount.length();
        if (tradeAmount.length() == 1) {
            rs = "00000000000" + tradeAmount;
        } else if (tradeAmount.length() == 2) {
            rs = "0000000000" + tradeAmount;
        }else if (tradeAmount.length() == 3) {
            rs = "000000000" + tradeAmount;
        }else if (tradeAmount.length() == 4) {
            rs = "00000000" + tradeAmount;
        }else if (tradeAmount.length() == 5) {
            rs = "0000000" + tradeAmount;
        }else if (tradeAmount.length() == 6) {
            rs = "000000" + tradeAmount;
        }else if (tradeAmount.length() == 7) {
            rs = "00000" + tradeAmount;
        }else if (tradeAmount.length() == 8) {
            rs = "0000" + tradeAmount;
        }else if (tradeAmount.length() == 9) {
            rs = "000" + tradeAmount;
        }else if (tradeAmount.length() == 10) {
            rs = "00" + tradeAmount;
        }else if (tradeAmount.length() == 11) {
            rs = "0" + tradeAmount;
        }else if (tradeAmount.length() == 12) {
            rs =  tradeAmount;
        }
        return rs;
    }

    /**
     * 根据应用编号获取应用名称
     * @param apptype
     * @return
     */
    public static String getType(String apptype){
        String appname = "";  //应用名称
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
            case TYPE_WATERWAY:
                appname = "水费缴纳";
                break;
            default:
                break;
        }
        return appname;
    }

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
    /**
     * 根据不同的应用名称交易类型打印交易类型字段
     * @param type
     * @param apptype
     * @return
     */
    public static String getTypeName(String type, String apptype){
        String appname = "";  //应用名称
        String typename = ""; //交易类型名称
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
            case TYPE_WATERWAY:
                appname = "水费缴纳";
                break;
            case TYPE_UMPAY:
                appname = "和包支付";
                break;
            default:
                appname = "未知";
                break;

        }

        switch (type){
            case TRANSACTION_CONSUME:
                typename = appname + "消费";
                break;
            case TRANSACTION_CONSUME_CANCEL:
                typename = appname + "消费撤销";
                break;
            case TRANSACTION_CHARGE_CANCEL:
                typename = appname + "充值撤销";
                break;
            case TRANSACTION_RETURN:
                typename = appname + "退货";
                break;
            case TRANSACTION_CHARGE:
                typename = appname + "充值";
                break;
            case TRANSACTION_ACCOUNT_CHARGE:
                typename = appname + "账户充值";
                break;
            case TRANSACTION_CASH_CHARGE:
                typename = appname + "现金充值";
                break;
            case TRANSACTION_ACTUALIZER:
                typename = appname + "补登";
                break;
            default:
                typename = appname + "未知";
                break;
        }

        return typename;
    }

    /**格式化时间格式*/
    public static String formateDateTieme(String datetimestr){
        StringBuffer sbuffer = new StringBuffer();
        String date1 = datetimestr.substring(0,4);
        sbuffer.append(date1);
        sbuffer.append("-");
        String date2 = datetimestr.substring(4,6);
        sbuffer.append(date2);
        sbuffer.append("-");
        String date3 = datetimestr.substring(6,8);
        sbuffer.append(date3);
        sbuffer.append(" ");
        String date4 = datetimestr.substring(8,10);
        sbuffer.append(date4);
        sbuffer.append(":");
        String date5 = datetimestr.substring(10,12);
        sbuffer.append(date5);
        sbuffer.append(":");
        String date6 = datetimestr.substring(12,14);
        sbuffer.append(date6);
        return sbuffer.toString();
    }

    public static String formatDatePrint(String datetime){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<14;i++){
            if (i==4||i==6){
                stringBuilder.append("/");
            }else if(i==8){
                stringBuilder.append(" ");
            }else if (i==10||i==12){
                stringBuilder.append(":");
            }
            stringBuilder.append(datetime.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String formatDate(String date){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<8;i++){
            if (i==4||i==6){
                stringBuilder.append("/");
            }
            stringBuilder.append(date.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String formatEWalletAmount(String amount){
//        Double moneyDouble = Double.parseDouble(amount) * 100;
//        int i = (new Double(moneyDouble)).intValue();

        BigDecimal bigDecimal = new BigDecimal(amount);
        BigDecimal bigDecimal100 = new BigDecimal("100");
        int i = bigDecimal.multiply(bigDecimal100).intValue();

//        String amountstr = String.valueOf(i);
        String amountstr = IntToHex(i);
        return fillLeft(amountstr,"0",8);
    }

    /**
     * 比较两个字符串时间大小，如果time1早于time2返回true,否则返回false
     * */
    public static boolean compare(String time1, String time2) throws ParseException
    {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        //将字符串形式的时间转化为Date类型的时间
        Date a=sdf.parse(time1);
        Date b=sdf.parse(time2);
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if(a.before(b))
            return true;
        else
            return false;
		/*
		 * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
		if(a.getTime()-b.getTime()<0)
			return true;
		else
			return false;
		*/
    }

    /**
     * 电子钱包余额格式转换
     * */

    public static String formatEpAmount(String amount){
        float money = bytesToAmount(hexStringToBinary(amount));
        return String.valueOf(money);
    }

    public static float bytesToAmount(byte[] data)
    {
        int num = 0;
        int index = 0;
        for (int i = data.length - 1; i >= 0; i--)
        {
            num += data[i] * (int) Math.pow(0x100, index);
            index++;
        }
        return (float)num / 100;
    }

    /**
     * 电子钱包交易记录脱机序号格式转换
     * */
    public static String formatEpSerial(String serial){
        long sno = Long.valueOf(serial.toUpperCase(), 16);
        String serialstr = fillLeft(String.valueOf(sno),"0",4);
        return serialstr;
    }

    private static int bytesToSerialt(byte[] data)
    {
        int num = 0;
        int index = 0;
        for (int i = data.length - 1; i >= 0; i--)
        {
            num += data[i] * (int) Math.pow(0x100, index);
            index++;
        }
        return num;
    }

    //10进制转16进制
    public static String IntToHex(int n){
        char[] ch = new char[20];
        int nIndex = 0;
        while ( true ){
            int m = n/16;
            int k = n%16;
            if ( k == 15 )
                ch[nIndex] = 'F';
            else if ( k == 14 )
                ch[nIndex] = 'E';
            else if ( k == 13 )
                ch[nIndex] = 'D';
            else if ( k == 12 )
                ch[nIndex] = 'C';
            else if ( k == 11 )
                ch[nIndex] = 'B';
            else if ( k == 10 )
                ch[nIndex] = 'A';
            else
                ch[nIndex] = (char)('0' + k);
            nIndex++;
            if ( m == 0 )
                break;
            n = m;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(ch, 0, nIndex);
        sb.reverse();
//        String strHex = new String("0x");
//        strHex += sb.toString();
        return sb.toString();
    }

    // 16进制转10进制
    public static int HexToInt(String strHex) {
        int nResult = 0;
        if (!IsHex(strHex))
            return nResult;
        String str = strHex.toUpperCase();
        if (str.length() > 2) {
            if (str.charAt(0) == '0' && str.charAt(1) == 'X') {
                str = str.substring(2);
            }
        }
        int nLen = str.length();
        for (int i = 0; i < nLen; ++i) {
            char ch = str.charAt(nLen - i - 1);
            try {
                nResult += (GetHex(ch) * GetPower(16, i));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return nResult;
    }

    // 计算16进制对应的数值
    public static int GetHex(char ch) throws Exception {
        if (ch >= '0' && ch <= '9')
            return (int) (ch - '0');
        if (ch >= 'a' && ch <= 'f')
            return (int) (ch - 'a' + 10);
        if (ch >= 'A' && ch <= 'F')
            return (int) (ch - 'A' + 10);
        throw new Exception("error param");
    }

    // 计算幂
    public static int GetPower(int nValue, int nCount) throws Exception {
        if (nCount < 0)
            throw new Exception("nCount can't small than 1!");
        if (nCount == 0)
            return 1;
        int nSum = 1;
        for (int i = 0; i < nCount; ++i) {
            nSum = nSum * nValue;
        }
        return nSum;
    }

    // 判断是否是16进制数
    public static boolean IsHex(String strHex) {
        int i = 0;
        if (strHex.length() > 2) {
            if (strHex.charAt(0) == '0'
                    && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                i = 2;
            }
        }
        for (; i < strHex.length(); ++i) {
            char ch = strHex.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F')
                    || (ch >= 'a' && ch <= 'f'))
                continue;
            return false;
        }
        return true;
    }

    /**计算两个十六进制字符串差*/
    public static String formatEpBlanceSubtract(String epBalance, String amount){
        Long ep1 = Long.valueOf(epBalance, 16);
        Long ep2 = Long.valueOf(amount, 16);
        Long ep = ep1 - ep2;
        String epstr = Long.toHexString(ep);
        return fillLeft(epstr,"0",8);
    }

    /**计算两个十六进制字符串和*/
    public static String formatEpBlanceAdd(String epBalance, String amount){
        Long ep1 = Long.valueOf(epBalance, 16);
        Long ep2 = Long.valueOf(amount, 16);
        Long ep = ep1 + ep2;
        String epstr = Long.toHexString(ep);
        return fillLeft(epstr,"0",8);
    }

    /**
     * 转字符串成十六进制的ASCII
     * @param str
     * @return
     */
    public static String convertStringToHex(String str){
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }
        return hex.toString();
    }

    /**
     * 去掉字符串后面为0的字符
     * */
    public static String delZero(String tempString) {

        int initlen = tempString.length();
        int finallen = initlen;
        int st = 0;
        int off = 0;
        char[] val = new char[initlen];
        tempString.getChars(0, finallen, val, 0);

        //while ((st < finallen) && (val[off + st] <= '0')) {
        //    st++;
        //}去掉头空格
        while ((st < finallen) && (val[off + finallen - 1] <= '0')) {
            finallen--;
        }
        return ((st > 0) || (finallen < initlen)) ? tempString.substring(st, finallen) : tempString;
    }
}
