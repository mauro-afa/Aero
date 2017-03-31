package com.example.mauro.test1;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Scroller;


public class configuracion extends Fragment {
    EditText a;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configuracion, container, false);

        /*a = (EditText) rootView.findViewById(R.id.editText);
        a.setVerticalScrollBarEnabled(true);
        a.setMovementMethod(new ScrollingMovementMethod());*/
        return rootView;
    }
    public configuracion() {

        // Required empty public constructor
    }
}
