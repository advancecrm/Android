package com.AndroidTestWsdl2Code;


import java.io.ByteArrayOutputStream;
import com.Wsdl2Code.WebServices.SampleService.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.SampleService.SampleService;
import com.Wsdl2Code.WebServices.SampleService.VectorByte;
import com.Wsdl2Code.WebServices.SampleService.WS_Enums.TestEnum;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class AndroidTestWsdl2Code extends Activity implements IWsdl2CodeEvents {
    
	
	public void callWebService(){
	        SampleService srv1 = new SampleService(this);
	        Drawable drw = getResources().getDrawable(R.drawable.icon);
	        Bitmap bitmap = ((BitmapDrawable)drw).getBitmap();
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	        byte[] bitmapdata = stream.toByteArray();
	        
	        try {
				srv1.GetBackByteArrayAsync(new VectorByte(bitmapdata));
				srv1.GetStringAsync();
		        srv1.GetDoubleAsync();
		        srv1.GetEnumAsync();
		        srv1.GetInt16Async();
		        srv1.GetInt32Async();
		        srv1.GetInt64Async();
		        srv1.GetListOfCustomObjectAsync();
		        srv1.getListStringsAsync();
		        srv1.sendEnumAsync(TestEnum.TestEnum1);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new AsyncTask<Void, Void, Void>() {
        	@Override
        	protected Void doInBackground(Void... params) {
        		callWebService();
				return null;
        	}
		}.execute();
    }
	@Override
	public void Wsdl2CodeStartedRequest() {
		Log.e("Wsdl2Code", "Wsdl2CodeStartedRequest");
		
	}
	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		Log.e("Wsdl2Code", "Wsdl2CodeFinished");
		Log.e("Wsdl2Code",methodName);
		
	}
	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {
		Log.e("Wsdl2Code", "Wsdl2CodeFinishedWithException");
		
	}
	@Override
	public void Wsdl2CodeEndedRequest() {
		Log.e("Wsdl2Code", "Wsdl2CodeEndedRequest");
	}
}