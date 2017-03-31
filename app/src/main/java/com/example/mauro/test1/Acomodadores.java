package com.example.mauro.test1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mfigueroa on 26/06/2015.
 */
public class Acomodadores extends Fragment {
    public Acomodadores() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.acomodadores, container, false);
        return rootView;
    }
}
