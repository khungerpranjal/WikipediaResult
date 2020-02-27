package com.moneytap.wikipediaresult.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.moneytap.wikipediaresult.R
import kotlinx.android.synthetic.main.detailed_view.*

class DetailedViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null) {
            webview.setWebViewClient(Browser())
            webview.getSettings().setLoadsImagesAutomatically(true)
            webview.getSettings().setJavaScriptEnabled(true)
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
            webview.loadUrl(intent.getStringExtra("url"))
        }
    }

    private inner class Browser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }




}