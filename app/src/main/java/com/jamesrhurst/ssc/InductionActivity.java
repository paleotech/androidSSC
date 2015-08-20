package com.jamesrhurst.ssc;

/*
 * Class InductionActivity manages the induction screen.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 */
public class InductionActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SurgerySafetyChecklist.EXTRA_MESSAGE);


        setContentView(R.layout.checklist_layout);
        setListAdapter(new InductionAdapter());
        TextView newtext = (TextView) findViewById(R.id.ListitemDescriptionBar);
        newtext.setText(R.string.induction_title);
    }

    /**
     * A simple array adapter that creates a list of ChecklistItem.
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

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.checklist_item, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
            return convertView;
        }
    }
}