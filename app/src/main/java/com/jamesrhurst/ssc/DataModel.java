package com.jamesrhurst.ssc;

/**
 * Created by jhurst on 8/24/15.
 */

import android.view.Menu;

import java.util.HashMap;
import java.util.Map;

public class DataModel {

    private static Map<String, String> inductionMap;
    private static Map<String, String> incisionMap;
    private static Map<String, String> signoutMap;

    private static DataModel instance = null;

    protected DataModel() {
    }

    public static DataModel getInstance()
    {
        if (instance == null) {
            instance = new DataModel();
            instance.inductionMap = new HashMap<>();
            instance.incisionMap = new HashMap<>();
            instance.signoutMap = new HashMap<>();
            instance.initializeAll();
        }
        return instance;
    }
    
    public void initializeAll()
    {
        for (int i = 0; i < ChecklistItem.INDUCTION.length; i++)
            inductionMap.put(ChecklistItem.INDUCTION[i], "0");
        for (int i = 0; i < ChecklistItem.INCISION.length; i++)
            incisionMap.put(ChecklistItem.INCISION[i], "0");
        for (int i = 0; i < ChecklistItem.SIGNOUT.length; i++)
            signoutMap.put(ChecklistItem.SIGNOUT[i], "0");
    }

    public boolean isInductionItemChecked(String theItem) {
        if (inductionMap.get(theItem).equals("0"))
            return false;
        return true;
    }

    public boolean isIncisionItemChecked(String theItem) {
        if (incisionMap.get(theItem).equals("0"))
            return false;
        return true;
    }

    public boolean isSignoutItemChecked(String theItem) {
        if (signoutMap.get(theItem).equals("0"))
            return false;
        return true;
    }

    public void toggleItem(String type, String text)
    {
        if (type.equals("induction"))
        {
            if (inductionMap.get(text).equals("0")) {
                inductionMap.remove(text);
                inductionMap.put(text, "1");
            }
            else {
                inductionMap.remove(text);
                inductionMap.put(text, "0");
            }

        }
        else if (type.equals("incision"))
        {
            if (incisionMap.get(text).equals("0")) {
                incisionMap.remove(text);
                incisionMap.put(text, "1");
            }
            else {
                incisionMap.remove(text);
                incisionMap.put(text, "0");
            }

        }
        else if (type.equals("signout"))
        {
            if (signoutMap.get(text).equals("0")) {
                signoutMap.remove(text);
                signoutMap.put(text, "1");
            }
            else {
                signoutMap.remove(text);
                signoutMap.put(text, "0");
            }

        }
    }

    public void clearItem(String type, String text)
    {
        if (type.equals("induction"))
        {
            inductionMap.remove(text);
            inductionMap.put(text, "0");
        }
        else if (type.equals("incision"))
        {
            incisionMap.remove(text);
            incisionMap.put(text, "0");
        }
        else if (type.equals("signout"))
        {
            signoutMap.remove(text);
            signoutMap.put(text, "0");
        }
    }

    public int fetchInt(int screen, String theText)
    {
        if (screen == 0)
        {
            if (inductionMap.get(theText).equals("0"))
                return 0;
            else
                return 1;
        }
        else if (screen == 1)
        {
            if (incisionMap.get(theText).equals("0"))
                return 0;
            else
                return 1;
        }
        else if (screen == 2)
        {
            if (signoutMap.get(theText).equals("0"))
                return 0;
            else
                return 1;
        }
        return 0;
    }

    public int[] getScreenState(int screen) {
        int[] theArray = new int[10];
        if (screen == 0)
        {
            for (int i = 0; i < 4; i++)
            {
                String theKey = ChecklistItem.INDUCTION[i];
                theArray[i] = fetchInt(screen, theKey);
            }
            String theKey = ChecklistItem.INDUCTION[4];
            int marked = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[5];
            int markingNA = fetchInt(screen, theKey);
            if ( (marked == 1) || (markingNA == 1) )
                theArray[4] = 1;
            else
                theArray[4] = 0;

            theKey = ChecklistItem.INDUCTION[6];
            theArray[5] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[7];
            theArray[6] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[9];
            int allergy = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[10];
            int noKnownAllergy = fetchInt(screen, theKey);
            if (allergy == 1)
                theArray[7] = 2;
            else if (noKnownAllergy == 1)
                theArray[7] = 1;
            else
                theArray[7] = 0;
            theKey = ChecklistItem.INDUCTION[13];
            int airwayRisk = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[12];
            int noAirwayRisk = fetchInt(screen, theKey);
            if (airwayRisk == 1)
                    theArray[8] = 2;
            else if (noAirwayRisk == 1)
                theArray[8] = 1;
            else
                theArray[8] = 0;
            theKey = ChecklistItem.INDUCTION[16];
            int bloodlossRisk = fetchInt(screen, theKey);
            theKey = ChecklistItem.INDUCTION[15];
            int noBloodlossRisk = fetchInt(screen, theKey);
            if (bloodlossRisk == 1)
                    theArray[9] = 2;
            else if (noBloodlossRisk == 1)
                theArray[9] = 1;
            else
                theArray[9] = 0;
        } else if (screen == 1) {
            String theKey = ChecklistItem.INCISION[0];
            theArray[0] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[2];
            theArray[1] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[3];
            theArray[2] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[4];
            theArray[3] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[6];
            theArray[4] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[7];
            theArray[5] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[8];
            theArray[6] = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[10];
            int abxGiven = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[11];
            int abxNA = fetchInt(screen, theKey);
            if ( (abxGiven == 1) || (abxNA == 1) )
                theArray[7] = 1;
            else
                theArray[7] = 0;
            theKey = ChecklistItem.INCISION[13];
            int imaging = fetchInt(screen, theKey);
            theKey = ChecklistItem.INCISION[14];
            int imagingNA = fetchInt(screen, theKey);
            if ( (imaging == 1) || (imagingNA == 1) )
                theArray[8] = 1;
            else
                theArray[8] = 0;
        } else if (screen == 2) {
            for (int i = 1; i < ChecklistItem.SIGNOUT.length; i++)
            {
                String theKey = ChecklistItem.SIGNOUT[i];
                theArray[i-1] = fetchInt(screen, theKey);
            }
        }
        return theArray;
    }
}
