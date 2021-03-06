package com.jamesrhurst.ssc;
/*
 * Class IncisionActivity manages the Incision screen. It contains a list of checkboxes.
 * Each checkbox has three states: a green checkbox, unchecked while it's partner is unchecked, or
 * unchecked, and requiring that either it or its partnet be checked (the alert state).
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

public class IncisionActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.checklist_layout);
        setListAdapter(new IncisionAdapter());
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
            intent.putExtra("PageToLoad", "Incision");
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
        theDM.toggleItem("incision", text);
        ImageView theView = (ImageView) v;
        final int position = listView.getPositionForView(il);
        checkImageConstraints(v, il, text, position);
    }

    /**
     * A simple array adapter that creates the list of ChecklistItems.
     */
    private class IncisionAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return ChecklistItem.INCISION.length;
        }

        @Override
        public String getItem(int position) {

            return ChecklistItem.INCISION[position];
        }

        @Override
        public long getItemId(int position) {
            return ChecklistItem.INCISION[position].hashCode();
        }

        public boolean isIndented(final int position) {
            String theString = ChecklistItem.INCISION[position];
            String[] theArray = ChecklistItem.IncisionIndents;
            if (Arrays.asList(theArray).contains(theString))
                return true;
            return false;
        }

        public boolean hideImage(final int position) {
            String theString = ChecklistItem.INCISION[position];
            String[] theArray = ChecklistItem.IncisionTitles;
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

    /*
        This is the tricky part, and it's not that tricky: some of the checkboxes are paired,
        so that the function as two-item radio boxes. This method manages the model and the image display.
     */
    public void checkImageConstraints(View convertView, InductionLayout container, String theString, int position) {
        ImageView theImage = (ImageView) convertView.findViewById(R.id.checklist_image);
        DataModel theDM = DataModel.getInstance();
        if (theString.equals(ChecklistItem.INCISION[10]) ) {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isIncisionItemChecked(theString))
                theDM.clearItem("incision", ChecklistItem.INCISION[11]);
            if (theDM.isIncisionItemChecked(ChecklistItem.INCISION[11]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INCISION[11]) )
        {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isIncisionItemChecked(theString))
                theDM.clearItem("incision", ChecklistItem.INCISION[10]);
            if (theDM.isIncisionItemChecked(ChecklistItem.INCISION[10]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INCISION[13]) ) {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isIncisionItemChecked(theString))
                theDM.clearItem("incision", ChecklistItem.INCISION[14]);
            if (theDM.isIncisionItemChecked(ChecklistItem.INCISION[14]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INCISION[14]) )
        {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isIncisionItemChecked(theString))
                theDM.clearItem("incision", ChecklistItem.INCISION[13]);
            if (theDM.isIncisionItemChecked(ChecklistItem.INCISION[13]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else
        {
            if (theDM.isIncisionItemChecked(theString))
                theImage.setImageResource(R.drawable.checklist_item_checked);
            else
                theImage.setImageResource(R.drawable.checklist_item_alert);
        }
    }

    /*
        If the current item is checked, make sure its partner is unchecked. If it's unchecked, and the partner exists,
        the partner should be on alert, rather than unchecked.
     */
    public void manageExclusivePair(int position, int offset, View convertView, InductionLayout container, String theString) {
        ImageView theImage = (ImageView) convertView.findViewById(R.id.checklist_image);
        DataModel theDM = DataModel.getInstance();
        if (theDM.isIncisionItemChecked(theString)) {
            theImage.setImageResource(R.drawable.checklist_item_checked);
            InductionLayout il = (InductionLayout) container;
            ListView lv = (ListView) il.getParent();
            if (lv != null) {
                int last = lv.getLastVisiblePosition();
                int first = lv.getFirstVisiblePosition();
                int partnerPosition = position + offset - first;
                if ((partnerPosition >= 0) && (partnerPosition <= last)) {
                    InductionLayout il2 = (InductionLayout) lv.getChildAt(partnerPosition);
                    ImageView otherImage = (ImageView) il2.findViewById(R.id.checklist_image);
                    otherImage.setImageResource(R.drawable.checklist_item_unchecked);
                }
            }
        }
        else
        {
            theImage.setImageResource(R.drawable.checklist_item_alert);
            InductionLayout il = (InductionLayout) container;
            ListView lv = (ListView) il.getParent();
            if (lv != null) {
                int last = lv.getLastVisiblePosition();
                int first = lv.getFirstVisiblePosition();
                int partnerPosition = position + offset - first;
                if ( (partnerPosition >= 0) && (partnerPosition <= last)) {
                    InductionLayout il2 = (InductionLayout) lv.getChildAt(partnerPosition);
                    ImageView otherImage = (ImageView) il2.findViewById(R.id.checklist_image);
                    otherImage.setImageResource(R.drawable.checklist_item_alert);
                }
            }
        }

    }
}
