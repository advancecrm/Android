package com.Wsdl2Code.WebServices.SampleService;

//------------------------------------------------------------------------------
// <wsdl2code-generated>
//    This code was generated by http://www.wsdl2code.com version  2.2
//
// Date Of Creation: 2/20/2013 11:16:36 PM
//    Please dont change this code, regeneration will override your changes
//</wsdl2code-generated>
//
//------------------------------------------------------------------------------
//
//This source code was auto-generated by Wsdl2Code  Version
//

public interface IWsdl2CodeEvents {
    public void Wsdl2CodeStartedRequest();
    public void Wsdl2CodeFinished(String methodName,Object Data);
    public void Wsdl2CodeFinishedWithException(Exception ex);
    public void Wsdl2CodeEndedRequest();
}
