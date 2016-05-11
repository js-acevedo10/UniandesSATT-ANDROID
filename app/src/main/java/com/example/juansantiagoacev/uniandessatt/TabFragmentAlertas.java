package com.example.juansantiagoacev.uniandessatt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by juansantiagoacev on 5/10/16.
 */
public class TabFragmentAlertas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_alertas, container, false);
    }

    public static String ARG_SECTION_NUMBER = "Section ";

    public TabFragmentAlertas() {
    }
}
