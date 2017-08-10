package com.example.user.bodymanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Seok on 2017-06-20.
 */

public class WebActivity extends BodygraphActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String n = intent.getStringExtra("exer");
        wv = (WebView) findViewById(R.id.webview1);

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        if(n.equals("벤치 프레스 - 머신"))
            wv.loadUrl("http://tv.naver.com/v/327985");
        else if(n.equals("벤치 프레스 - 바벨, 플랫"))
            wv.loadUrl("http://tv.naver.com/v/327986");
        else if(n.equals("풀오버 - 덤벨, 플랫"))
            wv.loadUrl("http://tv.naver.com/v/327994");
        else if(n.equals("숄더 프레스 - 머신"))
            wv.loadUrl("http://tv.naver.com/v/328000");
        else if(n.equals("비하인드 넥 프레스 - 스미스 머신"))
            wv.loadUrl("http://tv.naver.com/v/328001");
        else if(n.equals("아놀드 프레스 - 머신"))
            wv.loadUrl("http://tv.naver.com/v/328005");
        else if(n.equals("컬 - 바벨"))
            wv.loadUrl("http://tv.naver.com/v/328013");
        else if(n.equals("컬 - 덤벨"))
            wv.loadUrl("http://tv.naver.com/v/328015");
        else if(n.equals("컬 프레스 - 덤벨"))
            wv.loadUrl("http://tv.naver.com/v/328011");
        else if(n.equals("트라이셉스 익스텐션 - 덤벨, 라잉"))
            wv.loadUrl("http://tv.naver.com/v/328022");
        else if(n.equals("리버스 리스트 컬 - 바벨"))
            wv.loadUrl("http://tv.naver.com/v/328031");
        else if(n.equals("조트맨 컬 - 덤벨"))
            wv.loadUrl("http://tv.naver.com/v/328033");
        else if(n.equals("랫 풀 다운 - 머신"))
            wv.loadUrl("http://tv.naver.com/v/328036");
        else if(n.equals("로우 - 티바"))
            wv.loadUrl("http://tv.naver.com/v/328041");
        else if(n.equals("데드리프트 - 덤벨"))
            wv.loadUrl("http://tv.naver.com/v/328044");
        else if(n.equals("백 익스텐션"))
            wv.loadUrl("http://tv.naver.com/v/328046");
        else if(n.equals("싯업"))
            wv.loadUrl("http://tv.naver.com/v/328047");
        else if(n.equals("크로스 크런치"))
            wv.loadUrl("http://tv.naver.com/v/328059");
        else if(n.equals("V업"))
            wv.loadUrl("http://tv.naver.com/v/328054");
        else if(n.equals("사이드 힙 킥"))
            wv.loadUrl("http://tv.naver.com/v/328242");
        else if(n.equals("점프 스쿼트"))
            wv.loadUrl("http://tv.naver.com/v/328259");
        else if(n.equals("와이드 스쿼트"))
            wv.loadUrl("http://tv.naver.com/v/328260");
        else if(n.equals("레그 컬 - 라잉"))
            wv.loadUrl("http://tv.naver.com/v/328070");
        else if(n.equals("멀티힙"))
            wv.loadUrl("http://tv.naver.com/v/328072");
        else if(n.equals("카프 레이즈 - 싱글 레그"))
            wv.loadUrl("http://tv.naver.com/v/328075");

    }

    @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}


