package com.vjs3.mapsnippet;

import java.io.InputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.vjs3.mapsnippet.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CurrentLocation extends Activity implements LocationListener{
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	double latitude;
	double longitude;
	
	//protected static final String STATIC_MAP_API_ENDPOINT_NYC = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7C40.718217,-73.998284";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curr);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		final String STATIC_MAP_API_ENDPOINT_CURR = "https://maps.googleapis.com/maps/api/staticmap?center=latitude,longitude&zoom=13&size=600x300&maptype=roadmap&markers=color:red%7Clabel:C%7Clatitude,longitude";
		AsyncTask<Void, Void, Bitmap> setImageFromUrl = new AsyncTask<Void, Void, Bitmap>(){
			@Override
			protected Bitmap doInBackground(Void... params) {
				Bitmap bmp = null;
		        HttpClient httpclient = new DefaultHttpClient();   
		        HttpGet request = new HttpGet(STATIC_MAP_API_ENDPOINT_CURR); 

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
					final ImageView iv = (ImageView) findViewById(R.id.imgcurr);
					iv.setImageBitmap(bmp);
				}
			}
		};
		setImageFromUrl.execute();
	}
	@Override
	public void onLocationChanged(Location location) {
		latitude= location.getLatitude();
		longitude= location.getLongitude();
		}

		@Override
		public void onProviderDisabled(String provider) {
		Log.d("Latitude","disable");
		}

		@Override
		public void onProviderEnabled(String provider) {
		Log.d("Latitude","enable");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("Latitude","status");
		}
		}