package com.jamesrhurst.ssc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Created by jhurst on 8/21/15.
 */
public class HelpViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        webView = (WebView) findViewById(R.id.helpView);
        webView.getSettings().setJavaScriptEnabled(true);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("PageToLoad");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("PageToLoad");
        }
        this.loadPage(newString);
    }

    public void loadPage(String thePage)
    {
        if (thePage.equals("About"))
        {
            webView.loadUrl("file:///android_asset/about.html");
        }
        else if (thePage.equals("Induction"))
        {
            webView.loadUrl("file:///android_asset/inductionHelp.html");
        }
        else if (thePage.equals("Incision"))
        {
            webView.loadUrl("file:///android_asset/incisionHelp.html");
        }
        else if (thePage.equals("Signout"))
        {
            webView.loadUrl("file:///android_asset/signoutHelp.html");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String theTitle = new String((String) item.getTitle());
        Intent myIntent = new Intent(getApplicationContext(), SurgerySafetyChecklist.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
