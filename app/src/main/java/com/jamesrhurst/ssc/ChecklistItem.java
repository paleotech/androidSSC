package com.jamesrhurst.ssc;

/**
 * Created by jhurst on 8/19/15.
 * This is the three lists of checklist items for the checklist, segregated into
 * three static arrays of strings, which are the text lines for the checklist items.
 */

/**
 * Dummy data.
 */
public class ChecklistItem {
    public static final String[] INDUCTION = {
            "Identity confirmed by patient",
            "Site confirmed by patient",
            "Procedure confirmed by patient",
            "Patient consent confirmed",
            "Surgery site is marked",
            "Marking surgery site is not applicable",
            "Anesthesia safety check complete",
            "Pulse oximeter is on and workig",
            "Patient has a known allergy",
            "No known allergies",
            "No risk of difficult airway or aspiration",
            "Risk of difficult airway or aspiration, and equipment/assistance available",
            "No risk of > 500 ml blood loss (7 ml/kg in children)",
            "Risk of excessive blood loss, and adequate venous access and fluids prepared"

    };
    public static final String[] INCISION = {
            ""
    };
    public static final String[] SIGNOUT = {
            ""
    };
}