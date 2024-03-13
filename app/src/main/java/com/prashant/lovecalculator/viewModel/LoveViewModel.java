package com.prashant.lovecalculator.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prashant.lovecalculator.model.LoveModel;
import com.prashant.lovecalculator.repository.LoveRepository;

import org.json.JSONException;
import org.json.JSONObject;

public class LoveViewModel extends ViewModel {



    private MutableLiveData<LoveModel> loveResultLiveData = new MutableLiveData<>();
    private LoveRepository loveRepository = new LoveRepository();

    public void calculateLove(String firstName, String secondName) {
        loveRepository.calculateLove(firstName, secondName,
                response -> {
                    LoveModel loveResult = parseResponse(response);
                    loveResultLiveData.setValue(loveResult);
                },
                error -> {

                });
    }

    public LiveData<LoveModel> getLoveResult() {
        return loveResultLiveData;
    }

    private LoveModel parseResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);

            String fname = jsonResponse.getString("fname");
            String sname = jsonResponse.getString("sname");
            String percentage = jsonResponse.getString("percentage");
            String result = jsonResponse.getString("result");

            return new LoveModel(fname, sname, percentage, result);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
