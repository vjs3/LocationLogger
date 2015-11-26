package com.vjs3.mapsnippet;

import com.vjs3.mapsnippet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnLocation;
	Button btnCurr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
    
        btnLocation = (Button) findViewById(R.id.btnSave);
        btnCurr = (Button) findViewById(R.id.btnCurr);
    btnLocation.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {
        	
        	Intent intent = new Intent(MainActivity.this, SavedLocation.class);
       	 startActivity(intent);

        }

    });
    
    btnCurr.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {
        	
        	Intent intent = new Intent(MainActivity.this, CurrentLocation.class);
       	 startActivity(intent);

        }

    });
}
}