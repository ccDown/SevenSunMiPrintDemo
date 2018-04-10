package com.seven.sevensunmiprintdemo;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.seven.sevensunmiprintdemo.bean.CardInfo;
import com.seven.sevensunmiprintdemo.bean.ClearPrinterTemplet;
import com.seven.sevensunmiprintdemo.bean.EWalletConsumeCancelPrintTemplet;
import com.seven.sevensunmiprintdemo.bean.EWalletConsumePrintTemplet;
import com.seven.sevensunmiprintdemo.bean.MerchantAppTemplet;
import com.seven.sevensunmiprintdemo.bean.MobilePrinterTemplet;
import com.seven.sevensunmiprintdemo.bean.PrintItemObj;
import com.seven.sevensunmiprintdemo.bean.TransactionInfo;
import com.seven.sevensunmiprintdemo.utils.AidlUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;

import woyou.aidlservice.jiuiv5.ICallback;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private boolean isBold = false;
    private boolean isUnderLine = false;

    private CheckBox cbBold;
    private CheckBox cbUnderLine;
    private Button btnPrint;
    private EditText etPrintSize;
    private EditText etPrintText;
    private Button btnPrintMerchant;
    private Button btnPrintMobile;
    private Button btnPrintEwallet;
    private Button btnPrintEwalletCancel;
    private Button btnPrintClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbUnderLine = findViewById(R.id.cb_underline);
        cbBold = findViewById(R.id.cb_bold);
        btnPrint = findViewById(R.id.btn_print);
        etPrintSize = findViewById(R.id.et_printsize);
        etPrintText = findViewById(R.id.et_printtext);
        btnPrintMerchant = findViewById(R.id.btn_printmerchant);
        btnPrintMobile = findViewById(R.id.btn_printmobile);
        btnPrintEwallet = findViewById(R.id.btn_printewallt);
        btnPrintEwalletCancel = findViewById(R.id.btn_printewalltcancel);
        btnPrintClear = findViewById(R.id.btn_printeclear);

        cbBold.setOnCheckedChangeListener(this);
        cbUnderLine.setOnCheckedChangeListener(this);
        btnPrint.setOnClickListener(this);
        btnPrintMerchant.setOnClickListener(this);
        btnPrintMobile.setOnClickListener(this);
        btnPrintEwallet.setOnClickListener(this);
        btnPrintEwalletCancel.setOnClickListener(this);
        btnPrintClear.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.cb_bold:
                isBold = isChecked;
                break;
            case R.id.cb_underline:
                isUnderLine = isChecked;
                break;
        }
    }

    @Override
    public void onClick(View v) {

        //模拟数据
        TransactionInfo transcationInfo = new TransactionInfo();
        transcationInfo.setSerialno("000001");
        transcationInfo.setBatchno("000001");
        transcationInfo.setMerchantname("居民卡公司");
        transcationInfo.setMerchantno("010203010203010203");
        transcationInfo.setTerminalno("000000");
        transcationInfo.setOperatorno("00");
        transcationInfo.setMobileno("18100000000");
        transcationInfo.setApptype("1");
        transcationInfo.setType("1");
        transcationInfo.setVoucherno("000000");
        transcationInfo.setReferno("000000");
        transcationInfo.setAmount("123");
        transcationInfo.setTime("20202020202020");
        transcationInfo.setPoints("123");
        transcationInfo.setOriginalbatchno("000000");
        transcationInfo.setOriginalserialno("000000");

        CardInfo cardInfo = new CardInfo();
        cardInfo.setAddress("asd");
        cardInfo.setIssuerIdentifier("asd");
        cardInfo.setOfflineTransactionNo("asd");
        cardInfo.setKeyIndexNo("asd");
        cardInfo.setKeyVersionNo("000");
        cardInfo.setTerminalCode("000");
        cardInfo.setPsamOfflineTransactionNo("000");
        transcationInfo.setCardInfo(cardInfo);

        ArrayList<PrintItemObj> list = new ArrayList<>();

        switch (v.getId()) {
            case R.id.btn_print:
                list.add(new PrintItemObj(etPrintText.getText().toString(),Float.parseFloat(etPrintSize.getText().toString()),isBold));
                try {
                    AidlUtil.getInstance().printTextWithTrans(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            //打印商户小票
            case R.id.btn_printmerchant:
                MerchantAppTemplet merchantAppTemplet = new MerchantAppTemplet();
                list.addAll(merchantAppTemplet.printIntegra(transcationInfo,false));
                list.addAll(merchantAppTemplet.printCardHolder(transcationInfo,false));
                try {
                    AidlUtil.getInstance().printTextWithTrans(list, new ICallback.Stub() {
                        @Override
                        public void onRunResult(boolean isSuccess) throws RemoteException {
                            Log.e(TAG, "onRunResult: "+isSuccess );
                        }

                        @Override
                        public void onReturnString(String result) throws RemoteException {
                            Log.e(TAG, "onReturnString: "+result );
                        }

                        @Override
                        public void onRaiseException(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onRaiseException: "+code   +"    msg:"+msg );
                        }

                        @Override
                        public void onPrintResult(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onPrintResult: code "+code+"   msg"+msg );
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_printmobile:
                //打印移动积分小票
                MobilePrinterTemplet mobilePrinterTemplet = new MobilePrinterTemplet();
                list.addAll(mobilePrinterTemplet.printShops(transcationInfo,false));
                list.addAll(mobilePrinterTemplet.printCustomer(transcationInfo));
                try {
                    AidlUtil.getInstance().printTextWithTrans(list, new ICallback.Stub() {
                        @Override
                        public void onRunResult(boolean isSuccess) throws RemoteException {
                            Log.e(TAG, "onRunResult: "+isSuccess );
                        }

                        @Override
                        public void onReturnString(String result) throws RemoteException {
                            Log.e(TAG, "onReturnString: "+result );
                        }

                        @Override
                        public void onRaiseException(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onRaiseException: "+code   +"    msg:"+msg );
                        }

                        @Override
                        public void onPrintResult(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onPrintResult: code "+code+"   msg"+msg );
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btn_printewallt:
                EWalletConsumePrintTemplet eWalletConsumePrintTemplet = new EWalletConsumePrintTemplet();
                list.addAll(eWalletConsumePrintTemplet.printEWallet(transcationInfo,false));
                try {
                    AidlUtil.getInstance().printTextWithTrans(list, new ICallback.Stub() {
                        @Override
                        public void onRunResult(boolean isSuccess) throws RemoteException {
                            Log.e(TAG, "onRunResult: "+isSuccess );
                        }

                        @Override
                        public void onReturnString(String result) throws RemoteException {
                            Log.e(TAG, "onReturnString: "+result );
                        }

                        @Override
                        public void onRaiseException(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onRaiseException: "+code   +"    msg:"+msg );
                        }

                        @Override
                        public void onPrintResult(int code, String msg) throws RemoteException {
                            Log.e(TAG, "onPrintResult: code "+code+"   msg"+msg );
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case  R.id.btn_printewalltcancel:
                EWalletConsumeCancelPrintTemplet eWalletConsumeCancelPrintTemplet = new EWalletConsumeCancelPrintTemplet();
                list.addAll(eWalletConsumeCancelPrintTemplet.printEWallet(transcationInfo,false));
                try {
                    AidlUtil.getInstance().printTextWithTrans(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_printeclear:
                ArrayList<TransactionInfo>[] lists = (ArrayList<TransactionInfo>[]) Array.newInstance(ArrayList.class,1);
                lists[0] = new ArrayList<>();
                lists[0].add(transcationInfo);
                ClearPrinterTemplet clearPrinterTemplet = new ClearPrinterTemplet(lists);
                list.addAll(clearPrinterTemplet.printSettReport());
                list.addAll(clearPrinterTemplet.printTransactionList());
                try {
                    AidlUtil.getInstance().printTextWithTrans(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
                default:
                    break;
        }
    }
}
