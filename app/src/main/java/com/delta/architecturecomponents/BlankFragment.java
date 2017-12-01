package com.delta.architecturecomponents;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // setRetainInstance(true);
        ViewModelProvider of = ViewModelProviders.of(this);
        MainViewModel mainViewModel = of.get(MainViewModel.class);
        Log.e("fragment", "ViewModelProvider: " + of );
        MainViewModel mainViewModel1 = of.get(MainViewModel.class);
        getChildFragmentManager().beginTransaction().add(R.id.fl,new SecondFragment()).commit();
        Log.e("fragment", "onCreate: "+mainViewModel1.hashCode()+ mainViewModel.hashCode() );
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }




}
