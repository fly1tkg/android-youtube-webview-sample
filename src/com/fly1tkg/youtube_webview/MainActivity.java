
package com.fly1tkg.youtube_webview;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

public class MainActivity extends Activity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            WebView.class.getMethod("onResume").invoke(mWebView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            WebView.class.getMethod("onPause").invoke(mWebView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.webView);

        // WebViewの設定
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > 7) {
            settings.setPluginState(PluginState.ON);
        } else {
            settings.setPluginsEnabled(true);
        }

        String html = "";
        html += "<html><body>";
        html += "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/MU9Srs04sFU?rel=0\" frameborder=\"0\" allowfullscreen></iframe>";
        html += "</body></html>";

        mWebView.loadData(html, "text/html", null);
    }
}
