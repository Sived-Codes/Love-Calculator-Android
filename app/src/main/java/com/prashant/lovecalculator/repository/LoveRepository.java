package com.prashant.lovecalculator.repository;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prashant.lovecalculator.MyApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LoveRepository {
    private static final String LOVE_API_URL = "https://love-calculator.p.rapidapi.com/getPercentage";

    private static final String RAPID_API_KEY = "ceb1b263f8mshcea47ad9bd0435fp131987jsnea90af2285ac";
    private static final String RAPID_API_HOST = "love-calculator.p.rapidapi.com";

    public void calculateLove(String firstName, String secondName, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        try {
            String encodedFirstName = URLEncoder.encode(firstName, "UTF-8");
            String encodedSecondName = URLEncoder.encode(secondName, "UTF-8");

            String url = LOVE_API_URL + "?fname=" + encodedFirstName + "&sname=" + encodedSecondName;

            StringRequest request = new StringRequest(Request.Method.GET, url, successListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("X-RapidAPI-Key", RAPID_API_KEY);
                    headers.put("X-RapidAPI-Host", RAPID_API_HOST);
                    return headers;
                }
            };

            Volley.newRequestQueue(MyApplication.getAppContext()).add(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }
}
