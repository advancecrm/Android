package com.ballardscoreapp.main.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebserviceCall {
    String namespace = "http://tempuri.org/";
    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;
    private String url = "http://www.ballardscore.com/iphneservice/iphnservice.asmx?";

    public WebserviceCall() {
    }

    protected void SetEnvelope() {

        try {
            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            androidHttpTransport = new AndroidHttpTransport(url);
            androidHttpTransport.debug = true;
        } catch (Exception e) {
            System.out.println("Soap Exception---->>>" + e.toString());
        }
    }

    public String getConvertedWeight(String MethodName) {
        try {
            SOAP_ACTION = namespace + "GETFAQ";
            request = new SoapObject(namespace, MethodName);
            SetEnvelope();
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                String result = envelope.getResponse().toString();
                
                /**/

                return result;
            } catch (Exception e) {
                return e.toString();
            }
        } catch (Exception e) {
            return e.toString();
        }

    }

}
