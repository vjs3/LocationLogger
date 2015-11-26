package com.vjs3.mapsnippet;

import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.vjs3.mapsnippet.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SavedLocation extends Activity {
	
	
	protected static final String STATIC_MAP_API_ENDPOINT_NYC = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C40.718217,-73.998284";
	//protected static final String STATIC_MAP_API_ENDPOINT_LNMIIT = "https://maps.googleapis.com/maps/api/staticmap?center=26.935588,75.921596&zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C40.718217,-73.998284";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AsyncTask<Void, Void, Bitmap> setImageFromUrl = new AsyncTask<Void, Void, Bitmap>(){
			@Override
			protected Bitmap doInBackground(Void... params) {
				Bitmap bmp = null;
		        HttpClient httpclient = new DefaultHttpClient();   
		        HttpGet request = new HttpGet(STATIC_MAP_API_ENDPOINT_NYC); 

		        InputStream in = null;
		        //InputStream in2 = null;
		        try {
		            in = httpclient.execute(request).getEntity().getContent();
		            bmp = BitmapFactory.decodeStream(in);
		            in.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } 
				return bmp;
			}
			protected void onPostExecute(Bitmap bmp) {
				if (bmp!=null) {
					final ImageView iv = (ImageView) findViewById(R.id.imgnyc);
					iv.setImageBitmap(bmp);
				}
			}
		};
		setImageFromUrl.execute();
	}

	
}
