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
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//��������
		setContentView(R.layout.activity_home);
		
		title = (TextView)findViewById(R.id.title);
		title.setText("�� ҳ");
		
//		 WebView browser=(WebView)findViewById(R.id.Toweb);  
//	        browser.loadUrl("http://cclab.see.bupt.cn/index.php");  
//	          
//	        //���ÿ�����������ҳ  
//	        browser.getSettings().setSupportZoom(true);  
//	        browser.getSettings().setBuiltInZoomControls(true);  
//	          
//	        // ���ҳ�������ӣ����ϣ��������Ӽ����ڵ�ǰbrowser����Ӧ��  
//	        // �������¿�Android��ϵͳbrowser����Ӧ�����ӣ����븲��webview��WebViewClient����  
//	        browser.setWebViewClient(new WebViewClient() {  
//	            public boolean shouldOverrideUrlLoading(WebView view, String url)  
//	            {   
//	                //  ��д�˷������������ҳ��������ӻ����ڵ�ǰ��webview����ת��������������Ǳ�  
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
