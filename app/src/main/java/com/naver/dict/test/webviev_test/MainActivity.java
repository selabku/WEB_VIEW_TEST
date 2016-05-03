package com.naver.dict.test.webviev_test;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView mWebview = (WebView)findViewById(R.id.webview);
        WebSettings ws = mWebview.getSettings();


        ws.setJavaScriptEnabled(true);
        ws.setBuiltInZoomControls(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setSavePassword(false);
        ws.setDefaultTextEncodingName("EUC-KR");
        ws.setAllowFileAccess(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setDatabaseEnabled(true);
        ws.setDatabasePath("/data/data/" + getPackageName() + "/databases/");
        ws.setDomStorageEnabled(true);
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);

        if (Build.VERSION.SDK_INT >= 0x00000015) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cm = CookieManager.getInstance();
            cm.setAcceptThirdPartyCookies(mWebview, true);
        }

        if (Build.VERSION.SDK_INT >= 0x00000011) {
            //解决一部分高版本android手机网页音频不能自动播放的问题
            ws.setMediaPlaybackRequiresUserGesture(false);
        }


        if (Build.VERSION.SDK_INT == 0x00000015 && Build.MODEL.toLowerCase().contains("nexus")) {
            //解决webview在5.0的nexus手机下开始硬件加速造成的ui渲染问题
            mWebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mWebview.setVerticalScrollbarOverlay(true);
        //mWebview.setHorizontalScrollbarOverlay(true);

        mWebview.loadUrl("http://m.naver.com");
    }
}
