package com.example.desystem;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;

public class GoodsCaculateActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_title2);
	}

}
