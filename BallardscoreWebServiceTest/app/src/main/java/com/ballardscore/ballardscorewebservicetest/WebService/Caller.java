package com.ballardscore.ballardscorewebservicetest.WebService;

import com.ballardscore.ballardscorewebservicetest.FAQListActivity;

public class Caller extends Thread {
    public CallSoap cs;
    public int a,b;

    public void run(){
        try{
            cs=new CallSoap();
            String resp=cs.CallGetFAQ(a, b);
            FAQListActivity.rslt=resp;
        }catch(Exception ex)
        {FAQListActivity.rslt=ex.toString();}
    }
}