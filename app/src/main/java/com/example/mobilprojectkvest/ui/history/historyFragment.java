package com.example.mobilprojectkvest.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.mobilprojectkvest.R;
import com.example.mobilprojectkvest.databinding.FragmentHomeBinding;
import com.example.mobilprojectkvest.ui.home.HomeViewModel;

public class historyFragment extends Fragment
{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
     return root;
    }
}
