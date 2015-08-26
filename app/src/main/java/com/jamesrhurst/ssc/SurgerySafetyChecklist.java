package com.jamesrhurst.ssc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.content.Intent;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SurgerySafetyChecklist extends Activity {

    private ListView itemListView;
    private SSCAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        itemListView = (ListView) findViewById(R.id.topitems_list);
        arrayAdapter = new SSCAdapter();
        itemListView.setAdapter(arrayAdapter);

        // Add the callback
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), InductionActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(view.getContext(), IncisionActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(view.getContext(), SignoutActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

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

    private class SSCAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return ChecklistItem.TOPITEMS.length;
        }

        @Override
        public String getItem(int position) {

            return ChecklistItem.TOPITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return ChecklistItem.TOPITEMS[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.toplevel_item, container, false);
            }

            if (position != 3) {
                TextView theText = (TextView) convertView.findViewById(R.id.toplevel_text);
                theText.setText(ChecklistItem.TOPITEMS[position]);
                ImageView theImage = (ImageView) convertView.findViewById(R.id.toplevel_image);

                Bitmap theMap = drawImage(position);
                theImage.setImageBitmap(theMap);

                TextView text2 = (TextView) convertView.findViewById(R.id.toplevel_text2);
                text2.setText("");
            }
            else
            {
                DataModel theDM = DataModel.getInstance();
                int[] theArray = theDM.getScreenState(0);
                TextView theText = (TextView) convertView.findViewById(R.id.toplevel_text);
                if ( (theArray[7] == 0) && (theArray[8] == 0) && (theArray[9] == 0))
                {
                    theText.setText("");
                    TextView text2 = (TextView) convertView.findViewById(R.id.toplevel_text2);
                    text2.setText("");
                }
                else
                {
                    String theString = ChecklistItem.TOPITEMS[position];
                    theText.setText(theString);
                    String risk1 = "";
                    String risk2 = "";
                    String risk3 = "";
                    if (theArray[7] == 2)
                    {
                        risk1 =  "Allergy<br />";
                    }
                    if (theArray[8] == 2)
                    {
                        risk2 = "Airway<br />";
                    }
                    if (theArray[9] == 2)
                    {
                        risk3 = "Bloodloss<br />";
                    }
                    TextView text2 = (TextView) convertView.findViewById(R.id.toplevel_text2);
                    text2.setText(Html.fromHtml("<small>" + risk1 + "</small>" +
                            "<small>" + risk2 + "</small>" +
                            "<small>" + risk3 + "</small>"));
                }
            }

            return convertView;
        }

        public Bitmap drawImage(int position)
        {
            Bitmap greenIcon = BitmapFactory.decodeResource(getResources(), R.drawable.green_tile);
            Bitmap redIcon = BitmapFactory.decodeResource(getResources(), R.drawable.red_tile);
            Bitmap yellowIcon = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_tile);
            int spacing = greenIcon.getWidth() + 2;
            String theString = "spacing is " + spacing + " height is " + greenIcon.getHeight();
            //Toast.makeText(getApplicationContext(), theString, Toast.LENGTH_SHORT).show();
            Bitmap result = Bitmap.createBitmap((spacing * 10), greenIcon.getHeight() + 2, Bitmap.Config.ARGB_8888);
            if (position > 2)
                return result;
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();

            DataModel theDM = DataModel.getInstance();
            int[] theArray = theDM.getScreenState(position);
            int theEnd = 10;
            if (position == 1)
                theEnd = 9;
            else if (position == 2)
                theEnd = 5;
            for (int i = 0; i < theEnd; i++) {
                if (theArray[i] == 0)
                    canvas.drawBitmap(redIcon, spacing * i, 0, paint);
                else if (theArray[i] == 1)
                    canvas.drawBitmap(greenIcon, spacing * i, 0, paint);
                else if (theArray[i] == 2)
                    canvas.drawBitmap(yellowIcon, spacing * i, 0, paint);
            }
            return result;
        }
    }

    public void onClick(View theView) {
        DataModel theDm = DataModel.getInstance();
        theDm.initializeAll();
        arrayAdapter.notifyDataSetChanged();
    }
}
