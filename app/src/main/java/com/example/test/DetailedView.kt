package com.example.test

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detailed_view.*

class DetailedView : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_view)

        val intent = intent

//            url =
        if (intent.extras != null) {
            webview.setWebViewClient(MyBrowser())

            webview.getSettings().setLoadsImagesAutomatically(true)
            webview.getSettings().setJavaScriptEnabled(true)
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
            webview.loadUrl(intent.getStringExtra("url"))
        }
//            webview.loadUrl(intent.getStringExtra("url"))
    }

    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }




}