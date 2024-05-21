package com.example.androidapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OpenBrowserActivity extends AppCompatActivity {

    WebView browser;
    AutoCompleteTextView suggestedURL;
    ArrayAdapter<String> adapter;
    Button submit, back;
    String[] urls = {"google.com", "yahoo.com", "facebook.com", "youtube.com","github.com","figma.com"};
    boolean isButtonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_browser); // Use the correct layout file

        browser = findViewById(R.id.webView);
        suggestedURL = findViewById(R.id.actvURLGE10);
        submit = findViewById(R.id.btnOpenURLGE10);
        back = findViewById(R.id.btnBack);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urls);
        suggestedURL.setThreshold(1); // Set threshold to 1 to start suggesting URLs after 1 character
        suggestedURL.setAdapter(adapter);

        initializeWebView();
        loadWebURL();
    }

    public void initializeWebView() {
        browser.getSettings().setLoadsImagesAutomatically(true);
        // Enable JavaScript
        browser.getSettings().setJavaScriptEnabled(true);
        // Open web pages inside WebView
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        // Enable scrollbar
        browser.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
    }

    public void loadWebURL() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isButtonClicked = true; // Mark the button as clicked
                String url = suggestedURL.getText().toString();

                if (!url.startsWith("www.") && !url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "www." + url;
                }
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                browser.loadUrl(url);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (browser.canGoBack()) {
                    browser.goBack();
                }
            }
        });
    }
}
