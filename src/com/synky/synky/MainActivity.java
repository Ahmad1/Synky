package com.synky.synky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.navdrawer.SimpleSideDrawer;

public class MainActivity extends Activity implements OnClickListener {

	private Button profile, contacts, scan, preferences;
	private ImageButton slideMenu;
	static String contents;
	private SimpleSideDrawer mSlidingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mSlidingMenu = new SimpleSideDrawer( this );
	    mSlidingMenu.setLeftBehindContentView( R.layout.behind_menu_left );

		initViews();
		
		profile.setOnClickListener(this);
		contacts.setOnClickListener(this);
		scan.setOnClickListener(this);
		preferences.setOnClickListener(this);
		
		slideMenu.setOnClickListener(this);

	}

	private void initViews() {

		profile = (Button) findViewById(R.id.btn_profile);
		contacts = (Button) findViewById(R.id.btn_contacts);
		scan = (Button) findViewById(R.id.btn_scan);
		preferences = (Button) findViewById(R.id.btn_pref);
		
		slideMenu = (ImageButton) findViewById(R.id.image_btn);
		
		// -- to add custom font from asset folder
		TextView txt = (TextView) findViewById(R.id.tv_id);  
		Typeface font = Typeface.createFromAsset(getAssets(), "gatorade_bold.ttf");  
		txt.setTypeface(font); 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		Intent intent;

		switch (v.getId()) {
		case R.id.btn_contacts:
			intent = new Intent(getApplicationContext(), SynkyContacts.class);
			MainActivity.this.startActivity(intent);

			break;
		case R.id.btn_profile:
			intent = new Intent(getApplicationContext(), SynkyProfile.class);
			MainActivity.this.startActivity(intent);

			break;
		case R.id.btn_scan:

			Intent scanIntent = new Intent(
					"com.google.zxing.client.android.SCAN");
			scanIntent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(scanIntent, 0);

			break;
		case R.id.btn_pref:
			intent = new Intent(getApplicationContext(), SynkyPreferences.class);
			MainActivity.this.startActivity(intent);

			break;
		case R.id.image_btn:
			 mSlidingMenu.toggleLeftDrawer();

		default:
			break;
		}

	}

}
