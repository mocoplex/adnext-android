package com.mocoplex.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.mocoplex.adnext.sdk.AdnxtWebInterface;

public class HybridActivity extends AppCompatActivity {

    // 웹뷰
    private WebView m_webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid);

        // 웹뷰 로드
        m_webView = (WebView)findViewById(R.id.webView);
        m_webView.loadUrl("http://www.website.com/index.html");          // Web Url
        m_webView.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("HybridActivity", message + " -- From line " + lineNumber + " of " + sourceID);
            }
        });

        // AdNext SDK와 웹뷰의 연동 (하이브리드 앱 이벤트 연동)
        AdnxtWebInterface.enableBridgeScript(m_webView, this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(m_webView != null){
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if(m_webView.canGoBack()){
                    m_webView.goBack();
                    return true;
                }
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
