package com.example.igcleanup;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.ValueCallback;
import android.webkit.JavascriptInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    private WebView webView;
    private ValueCallback<Uri[]> fileCallback;
    private static final int FILE_PICKER = 1001;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        webView.addJavascriptInterface(new InstagramBridge(), "Android");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (fileCallback != null) fileCallback.onReceiveValue(null);
                fileCallback = callback;
                try { startActivityForResult(params.createIntent(), FILE_PICKER); }
                catch (Exception e) { fileCallback = null; return false; }
                return true;
            }
        });
        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    private class InstagramBridge {
        @JavascriptInterface public void openInstagramFollowing(String username) {
            if (username == null || !Pattern.matches("[A-Za-z0-9._]{1,30}", username)) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Please enter a valid Instagram username.", Toast.LENGTH_SHORT).show());
                return;
            }
            runOnUiThread(() -> {
                Uri uri = Uri.parse("https://www.instagram.com/" + username + "/following/");
                Intent appIntent = new Intent(Intent.ACTION_VIEW, uri);
                appIntent.setPackage("com.instagram.android");
                try {
                    startActivity(appIntent);
                } catch (Exception appNotAvailable) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    } catch (Exception noHandler) {
                        Toast.makeText(MainActivity.this, "Instagram or a browser could not be opened.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        @JavascriptInterface public void openInstagramProfile(String username) {
            if (username == null || !Pattern.matches("[A-Za-z0-9._]{1,30}", username)) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Please enter a valid Instagram username.", Toast.LENGTH_SHORT).show());
                return;
            }
            runOnUiThread(() -> {
                Uri uri = Uri.parse("https://www.instagram.com/" + username + "/");
                Intent appIntent = new Intent(Intent.ACTION_VIEW, uri);
                appIntent.setPackage("com.instagram.android");
                try {
                    startActivity(appIntent);
                } catch (Exception appNotAvailable) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    } catch (Exception noHandler) {
                        Toast.makeText(MainActivity.this, "Instagram or a browser could not be opened.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICKER && fileCallback != null) {
            Uri[] results = null;
            if (resultCode == Activity.RESULT_OK && data != null) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount(); results = new Uri[count];
                    for (int i=0;i<count;i++) results[i] = data.getClipData().getItemAt(i).getUri();
                } else if (data.getData() != null) results = new Uri[]{data.getData()};
            }
            fileCallback.onReceiveValue(results); fileCallback = null;
        }
    }

    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed();
    }
}