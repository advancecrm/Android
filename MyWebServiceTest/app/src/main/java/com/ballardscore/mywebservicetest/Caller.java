package com.ballardscore.mywebservicetest;

public class Caller extends Thread {
    public CallSoap cs;
    public int a,b;

    public void run(){
        try{
            cs=new CallSoap();
            String resp=cs.CallAdd(a, b);
            MainActivity.rslt=resp;
        }catch(Exception ex)
        {MainActivity.rslt=ex.toString();}
    }
}
