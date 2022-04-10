package com.example.mobilprojectkvest.ui.history;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobilprojectkvest.DataBase;
import com.example.mobilprojectkvest.MenuPageActivity;
import com.example.mobilprojectkvest.R;
import com.example.mobilprojectkvest.databinding.FragmentHistoryBinding;
import com.example.mobilprojectkvest.databinding.FragmentProfileBinding;

public class historyFragment extends Fragment
{

    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

     return root;
    }

}
