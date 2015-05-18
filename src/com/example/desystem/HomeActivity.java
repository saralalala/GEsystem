package com.example.desystem;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity {

	TextView title;
	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//不起作用
		setContentView(R.layout.activity_home);
		
		title = (TextView)findViewById(R.id.title);
		title.setText("首 页");
		
//		 WebView browser=(WebView)findViewById(R.id.Toweb);  
//	        browser.loadUrl("http://cclab.see.bupt.cn/index.php");  
//	          
//	        //设置可自由缩放网页  
//	        browser.getSettings().setSupportZoom(true);  
//	        browser.getSettings().setBuiltInZoomControls(true);  
//	          
//	        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，  
//	        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象  
//	        browser.setWebViewClient(new WebViewClient() {  
//	            public boolean shouldOverrideUrlLoading(WebView view, String url)  
//	            {   
//	                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边  
//	                view.loadUrl(url);  
//	                        return true;  
//	            }         
//	             });
	        
	        button = (ImageButton)findViewById(R.id.backbutton);
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
					
				}
			});
	}
	
//	
//	 @Override  
//	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
//	        WebView browser=(WebView)findViewById(R.id.Toweb);  
//	        // Check if the key event was the Back button and if there's history  
//	        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {  
//	            browser.goBack();  
//	            return true;  
//	        }  
//	      //  return true;  
//	        // If it wasn't the Back key or there's no web page history, bubble up to the default  
//	        // system behavior (probably exit the activity)  
//	        return super.onKeyDown(keyCode, event);  
//	    } 
	 
}
