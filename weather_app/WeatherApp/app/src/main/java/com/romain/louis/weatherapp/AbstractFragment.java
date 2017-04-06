package com.romain.louis.weatherapp;

import android.support.v4.app.Fragment;
import java.util.ArrayList;

/**
 * Created by romai on 24/03/2017.
 */

public abstract class AbstractFragment extends Fragment{
    public abstract void refresh();

    public abstract void orderListAZ();
}


