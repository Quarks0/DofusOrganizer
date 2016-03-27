package com.quarks0.dofusorganizer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CharacterSearch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String message = intent.getStringExtra(DisplayCharacters.GUILD_NAME);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        LinearLayout layout = (LinearLayout) findViewById(R.id.charsearchlayout);
        layout.addView(textView);
    }

    public static void loadWebpage(final Context context) {
        WebView view = new WebView(context);

        //Enables javascript to get the html
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(new MyJavaScriptInterface(context), "HtmlViewer");

        view.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:window.HtmlViewer.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });

        view.loadUrl("your url");
    }

    private static class MyJavaScriptInterface {
        private Context context;

        MyJavaScriptInterface(Context ctx) {
            context = ctx;
        }

        //NOTE: If your target API > 16 you must have @JavascriptInterface
        @SuppressWarnings("UnusedDeclaration")
        public void showHTML(String html) {
            //TODO what you need to with the html
        }
    }
}
