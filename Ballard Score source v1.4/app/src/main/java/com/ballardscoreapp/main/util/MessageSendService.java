package com.ballardscoreapp.main.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class MessageSendService {
    String namespace = "http://tempuri.org/";
    String SOAP_ACTION;
    SoapObject request = null, objMessages = null;
    SoapSerializationEnvelope envelope;
    AndroidHttpTransport androidHttpTransport;
    private String url = "http://www.ballardscore.com/iphneservice/iphnservice.asmx?";

    public MessageSendService() {
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

    public String getConvertedWeight(String MethodName, String firstname, String comment, String email, String subject) {
        try {
            SOAP_ACTION = namespace + MethodName;
            request = new SoapObject(namespace, MethodName);

            request.addProperty("Name", "" + firstname);
            request.addProperty("EmailID", "" + email);
            request.addProperty("Subject", "" + subject);
            request.addProperty("Comment", "" + comment);

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
