package com.example.madassignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    TextView tvUsername, tvEmail, tvPassword;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPassword = view.findViewById(R.id.tvPassword);

        loadPreferences();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences(); // auto-refresh on switching fragments
    }

    private void loadPreferences() {
        SharedPreferences sp = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        tvUsername.setText("Username: " + sp.getString("username", "Not Set"));
        tvEmail.setText("Email: " + sp.getString("email", "Not Set"));
        tvPassword.setText("Password: " + sp.getString("password", "Not Set"));
    }
}
