package com.example.mobilprojectkvest.ui.home;

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

import com.example.mobilprojectkvest.MainActivity;
import com.example.mobilprojectkvest.MenuPageActivity;
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
                Toast mapToast = Toast.makeText(HomeFragment.this.getActivity(), "map", Toast.LENGTH_SHORT);
                mapToast.show();
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