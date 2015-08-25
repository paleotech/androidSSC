package com.jamesrhurst.ssc;

/**
 * Created by jhurst on 8/19/15.
 * This is the three lists of checklist items for the checklist, segregated into
 * three static arrays of strings, which are the text lines for the checklist items.
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
            "Pulse oximeter is on and working",
            "Allergies",
            "Patient has a known allergy",
            "No known allergies",
            "Risk of difficult airway or aspiration",
            "No",
            "Yes and equipment/assistance available",
            "Risk of > 500 ml blood loss? (7 ml/kg in children)",
            "No risk of > 500 ml blood loss (7 ml/kg in children)",
            "Yes, and adequate venous access and fluids prepared"

    };

    public static final String[] InductionIndents = {
            "Surgery site is marked",
            "Marking surgery site is not applicable",
            "Patient has a known allergy",
            "No known allergies",
            "No",
            "Yes and equipment/assistance available",
            "No risk of > 500 ml blood loss (7 ml/kg in children)",
            "Yes, and adequate venous access and fluids prepared"
    };

    public static final String[] InductionTitles = {
                "Allergies",
                "Risk of difficult airway or aspiration",
                "Risk of > 500 ml blood loss? (7 ml/kg in children)",
    };

    public static final String[] INCISION = {
            "All team members have introduced themselves by name and role",
            "Surgeon, nurse and anesthesia provider verbally confirm",
            "Patient",
            "Site",
            "Prodecure",
            "Anticipated Critical Events",
            "Surgeon reviews critical or unexpectedsteps, duration, anticipated blood loss",
            "Anesthesia team reviews any patient specific concerns",
            "Nursing team reviews if sterility (including indicator results) is confirmed, any equipment issues or concerns",
            "Has antibiotic propylaxis been given within the last 60 minutes",
            "Yes",
            "Not Applicable",
            "Is essential imaging displayed?",
            "Yes",
            "Not Applicable"
    };

    public static final String[] IncisionTitles = {
            "Surgeon, nurse and anesthesia provider verbally confirm",
            "Anticipated Critical Events",
            "Has antibiotic propylaxis been given within the last 60 minutes",
            "Is essential imaging displayed?"
    };

    public static final String[] IncisionIndents = {
            "Patient",
            "Site",
            "Prodecure",
            "Surgeon reviews critical or unexpectedsteps, duration, anticipated blood loss",
            "Anesthesia team reviews any patient specific concerns",
            "Nursing team reviews if sterility (including indicator results) is confirmed, any equipment issues or concerns",
            "Yes",
            "Not Applicable",
    };
    public static final String[] SIGNOUT = {
            "Nurse verbally confirms with the team:",
            "The name of the procedure recorded",
            "That instrument, sponge, and needle counts are correct (or not applicable)",
            "How the specimen is labelled (including patient name)",
            "Whether there are any equipment problems to be addressed",
            "Surgeon, anesthesia provider, and nurse review the key concerns for recovery and patient management"
    };

    public static final String[] SignoutIndents = {
            "The name of the procedure recorded",
            "That instrument, sponge, and needle counts are correct (or not applicable)",
            "How the specimen is labelled (including patient name)",
            "Whether there are any equipment problems to be addressed"
    };

    public static final String[] SignoutTitles = {
            "Nurse verbally confirms with the team:"
    };


}