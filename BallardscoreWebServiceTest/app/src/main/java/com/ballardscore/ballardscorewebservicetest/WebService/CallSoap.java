package com.ballardscore.ballardscorewebservicetest.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CallSoap {
    public final String SOAP_ACTION = "http://tempuri.org/GETFAQ";

    public  final String ADD_OPERATION_NAME = "GETFAQ";

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public static final String SOAP_ADDRESS = "http://ballardscore.com/iphneservice/iphnservice.asmx";
    public CallSoap()
    {
    }
    /*public String CallGetFAQ()
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,ADD_OPERATION_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            //response = envelope.getResponse();
        }

    }*/
    public String CallGetFAQ(int a,int b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,ADD_OPERATION_NAME);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("a");
        pi.setValue(a);
        pi.setType(Integer.class);
        //request.addProperty(pi);
        pi=new PropertyInfo();
        pi.setName("b");
        pi.setValue(b);
        pi.setType(Integer.class);
        //request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
}
