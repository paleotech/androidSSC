package com.jamesrhurst.ssc;

/*
 * Class SignoutActivity manages the Signout screen.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Arrays;
/**
 *
 */
public class SignoutActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.checklist_layout);
        setListAdapter(new SignoutAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_child, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String theTitle = new String((String) item.getTitle());
        if (theTitle.equals("Help") )
        {
            Intent intent = new Intent(this, HelpViewActivity.class);
            intent.putExtra("PageToLoad", "Signout");
            startActivity(intent);
        }
        else {
            Intent myIntent = new Intent(getApplicationContext(), SurgerySafetyChecklist.class);
            startActivityForResult(myIntent, 0);
        }
        return true;
    }

    public void onClickBtn(View v) {
        // Get the model implementation for the ImageView passed in, by going to the parent, finding the associated string, and
        // using that as an index.
        LinearLayout ll = (LinearLayout) v.getParent();
        InductionLayout il =  (InductionLayout) ll.getParent();
        ListView listView = (ListView) il.getParent();
        TextView tv = (TextView)ll.findViewById(R.id.checklist_text);
        String text = tv.getText().toString();
        DataModel theDM = DataModel.getInstance();
        theDM.toggleItem("signout", text);
        ImageView theView = (ImageView) v;
        final int position = listView.getPositionForView(il);
        checkImageConstraints(v, il, text, position);
    }

    public void checkImageConstraints(View convertView, InductionLayout container, String theString, int position) {
        ImageView theImage = (ImageView) convertView.findViewById(R.id.checklist_image);
        DataModel theDM = DataModel.getInstance();
        if (theDM.isSignoutItemChecked(theString))
            theImage.setImageResource(R.drawable.checklist_item_checked);
        else
            theImage.setImageResource(R.drawable.checklist_item_alert);
    }

    /**
     * A simple array adapter that creates a list of ChecklistItems.
     */
    private class SignoutAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return ChecklistItem.SIGNOUT.length;
        }

        @Override
        public String getItem(int position) {

            return ChecklistItem.SIGNOUT[position];
        }

        @Override
        public long getItemId(int position) {
            return ChecklistItem.SIGNOUT[position].hashCode();
        }

        public boolean isIndented(final int position) {
            String theString = ChecklistItem.SIGNOUT[position];
            String[] theArray = ChecklistItem.SignoutIndents;
            if (Arrays.asList(theArray).contains(theString))
                return true;
            return false;
        }

        public boolean hideImage(final int position) {
            String theString = ChecklistItem.SIGNOUT[position];
            String[] theArray = ChecklistItem.SignoutTitles;
            if (Arrays.asList(theArray).contains(theString))
                return true;
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.checklist_item, container, false);
            }

            InductionLayout il = (InductionLayout) convertView;
            TextView theText = ((TextView) convertView.findViewById(R.id.checklist_text));
            String theString = getItem(position);
            theText.setText(theString);
            DataModel theDM = DataModel.getInstance();
            ImageView theImage = (ImageView) convertView.findViewById(R.id.checklist_image);
            checkImageConstraints(theImage, il, theString, position);

            // Indent and hide images as required
            ListView theList = (ListView) container;
            final int offset = theList.getFirstVisiblePosition();

            LinearLayout theLayout = (LinearLayout) convertView.findViewById(R.id.container);
            if (isIndented(position))
                theLayout.setPadding(80, 0, 0, 0);
            else
                theLayout.setPadding(0, 0, 0, 0);

            if (hideImage(position))
                theImage.setVisibility(View.GONE);
            else
                theImage.setVisibility(View.VISIBLE);

            return convertView;
        }
    }
}