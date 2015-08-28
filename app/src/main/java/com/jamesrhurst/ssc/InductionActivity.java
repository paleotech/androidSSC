package com.jamesrhurst.ssc;

/*
 * Class InductionActivity manages the induction screen.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

/**
 * 
 */
public class InductionActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.checklist_layout);
        setListAdapter(new InductionAdapter());
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
            intent.putExtra("PageToLoad", "Induction");
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
        theDM.toggleItem("induction", text);
        ImageView theView = (ImageView) v;
        final int position = listView.getPositionForView(il);
        checkImageConstraints(v, il, text, position);
    }

    /**
     * A simple array adapter that creates a list of ChecklistItems.
     */
    private class InductionAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return ChecklistItem.INDUCTION.length;
        }

        @Override
        public String getItem(int position) {

            return ChecklistItem.INDUCTION[position];
        }

        @Override
        public long getItemId(int position) {
            return ChecklistItem.INDUCTION[position].hashCode();
        }

        public boolean isIndented(final int position) {
            String theString = ChecklistItem.INDUCTION[position];
            String[] theArray = ChecklistItem.InductionIndents;
            if (Arrays.asList(theArray).contains(theString))
                return true;
            return false;
        }

        public boolean hideImage(final int position) {
            String theString = ChecklistItem.INDUCTION[position];
            String[] theArray = ChecklistItem.InductionTitles;
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
        if (theString.equals(ChecklistItem.INDUCTION[4]) ) {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[5]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[5]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[5]) )
        {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[4]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[4]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[9]) )
        {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[10]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[10]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[10]) ) {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[9]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[9]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[12]) )
        {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[13]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[13]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[13]) )
        {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[12]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[12]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[15]) )
        {
            manageExclusivePair(position, 1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[16]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[16]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else if (theString.equals(ChecklistItem.INDUCTION[16]) )
        {
            manageExclusivePair(position, -1, convertView, container, theString);
            if (theDM.isInductionItemChecked(theString))
                theDM.clearItem("induction", ChecklistItem.INDUCTION[15]);
            if (theDM.isInductionItemChecked(ChecklistItem.INDUCTION[15]))
                theImage.setImageResource(R.drawable.checklist_item_unchecked);
        }
        else
        {
            if (theDM.isInductionItemChecked(theString))
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
        if (theDM.isInductionItemChecked(theString)) {
            theImage.setImageResource(R.drawable.checklist_item_checked);
            InductionLayout il = (InductionLayout) container;
            ListView lv = (ListView) il.getParent();

            if (lv != null) {
                int last = lv.getLastVisiblePosition();
                int first = lv.getFirstVisiblePosition();
                int partnerPosition = position + offset - first;
                if ((partnerPosition >= 0) && (partnerPosition < last)) {
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
                if ( (partnerPosition >= 0) && (partnerPosition < last)) {
                    InductionLayout il2 = (InductionLayout) lv.getChildAt(partnerPosition);
                    ImageView otherImage = (ImageView) il2.findViewById(R.id.checklist_image);
                    otherImage.setImageResource(R.drawable.checklist_item_alert);
                }
            }
        }

    }
}