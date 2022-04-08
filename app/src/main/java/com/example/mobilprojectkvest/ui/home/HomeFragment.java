package com.example.mobilprojectkvest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.mobilprojectkvest.MapsActivity;
import com.example.mobilprojectkvest.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button createQuest = binding.CreateQuestBtn;
        createQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast createQuest = Toast.makeText(HomeFragment.this.getActivity(), "create quest", Toast.LENGTH_SHORT);
                createQuest.show();;

                return;
            }
        });

        Button map = binding.MapBtn;
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                return;
            }
        });

        Button exit = binding.ExitBtn;
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}