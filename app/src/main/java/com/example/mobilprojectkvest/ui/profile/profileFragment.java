package com.example.mobilprojectkvest.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mobilprojectkvest.MenuPageActivity;
import com.example.mobilprojectkvest.R;
import com.example.mobilprojectkvest.UpdateDateProfile;
import com.example.mobilprojectkvest.databinding.FragmentHomeBinding;
import com.example.mobilprojectkvest.databinding.FragmentProfileBinding;

public class profileFragment extends Fragment
{
    private TextView loginProfile;
    private TextView passwordProfile;

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loginProfile = binding.LoginProfile;
        passwordProfile = binding.PasswwordProfile;

        Bundle argument = getActivity().getIntent().getExtras();
        loginProfile.setText(argument.get("User").toString());
        passwordProfile.setText(argument.get("Password").toString());

        Button btn = binding.buttonDataUpdate;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateDateProfile.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
