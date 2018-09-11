package com.example.myfirstapp;

public class Caller extends Thread {
    public CallSoap cs;
    public int a,b;

    public void run(){
        try{
            cs=new CallSoap();
            String resp=cs.CallAdd(a, b);
            DisplayWebServiceActivity.rslt=resp;
        }catch(Exception ex)
        {DisplayWebServiceActivity.rslt=ex.toString();}
    }
}
