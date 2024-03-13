package com.prashant.lovecalculator.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.prashant.lovecalculator.databinding.ActivityMainBinding;
import com.prashant.lovecalculator.viewModel.LoveViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bind;

    private LoveViewModel loveViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        loveViewModel = new ViewModelProvider(this).get(LoveViewModel.class);

        bind.calculateBtn.setOnClickListener(v -> {

            bind.progressBar.setVisibility(View.VISIBLE);
            bind.mainLayout.setVisibility(View.GONE);

            String yourName = String.valueOf(bind.yourName.getText());
            String partnerName = String.valueOf(bind.partnerName.getText());

            if (yourName.isEmpty()){
                bind.progressBar.setVisibility(View.GONE);
                bind.mainLayout.setVisibility(View.VISIBLE);

                bind.yourName.requestFocus();
                bind.yourName.setError("Can't be empty !");

            }else if (partnerName.isEmpty()){
                bind.progressBar.setVisibility(View.GONE);
                bind.mainLayout.setVisibility(View.VISIBLE);

                bind.partnerName.requestFocus();
                bind.partnerName.setError("Can't be empty !");

            }else{
                loveViewModel.calculateLove(yourName, partnerName);

            }

        });

        loveViewModel.getLoveResult().observe(this, loveModel -> {
            if (loveModel != null) {
                bind.progressBar.setVisibility(View.GONE);
                bind.mainLayout.setVisibility(View.GONE);
                bind.resultLayout.setVisibility(View.VISIBLE);

                bind.resultView.setText(loveModel.getResult());
                bind.percentView.setText(loveModel.getPercentage());
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (bind.resultLayout.getVisibility()==View.VISIBLE){
            bind.resultLayout.setVisibility(View.GONE);
            bind.mainLayout.setVisibility(View.VISIBLE);
        }else{
            super.onBackPressed();
        }


    }
}