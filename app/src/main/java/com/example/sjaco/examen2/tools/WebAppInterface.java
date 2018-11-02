package com.example.sjaco.examen2.tools;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    private Context mContext;
    public WebAppInterface(Context c) {
        mContext = c;
    }
    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

}
