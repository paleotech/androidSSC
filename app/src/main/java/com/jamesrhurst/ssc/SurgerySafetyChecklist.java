package com.jamesrhurst.ssc;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ListView;
import android.widget.ArrayAdapter;



public class SurgerySafetyChecklist extends Activity {

    public final static String EXTRA_MESSAGE = "com.jamesrhurst.ssc.MESSAGE";
    private String[] itemArray = { "Before Induction", "Before Incision", "Before Sign Out", " "};

    private ListView itemListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        itemListView = (ListView) findViewById(R.id.topitems_list);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemArray);
        itemListView.setAdapter(arrayAdapter);

        // Add the callback
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), InductionActivity.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    Intent intent = new Intent(view.getContext(), IncisionActivity.class);
                    startActivity(intent);
                }
                else if (position == 2)
                {
                    Intent intent = new Intent(view.getContext(), SignoutActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_surgery_safety_checklist, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String theTitle = new String((String) item.getTitle());
        if (theTitle.equals("Help")) {
            Intent intent = new Intent(this, HelpViewActivity.class);
            intent.putExtra("PageToLoad", "About");
            startActivity(intent);
        }

        return true;
    }
}
