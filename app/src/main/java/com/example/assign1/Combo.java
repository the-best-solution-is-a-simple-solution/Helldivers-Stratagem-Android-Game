package com.example.assign1;

import static com.example.assign1.eComboState.*;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Combo implements Serializable
{
    private String title;
    private List<eDirection> lstCombo;
    private eComboState eComboState;

    public Combo(String title, List<eDirection> lstCombo)
    {
        this.title = title;
        this.lstCombo = lstCombo;
        this.eComboState = NOT_ATTEMPTED;
    }

    public String getTitle()
    {
        return this.title;
    }

    public List<eDirection> getCombinations()
    {
        return this.lstCombo;
    }

    public eComboState getComboState()
    {
        return this.eComboState;
    }

    public void setComboState(eComboState eState)
    {
        this.eComboState = eState;
    }

    @NonNull
    @Override
    public String toString()
    {
        String sReturn = String.format("%s: ", this.getTitle());

        for (eDirection dir : this.lstCombo)
        {
            sReturn += dir.toString() + " ";
        }

        return sReturn.substring(0, sReturn.length() - 1);
    }
}
