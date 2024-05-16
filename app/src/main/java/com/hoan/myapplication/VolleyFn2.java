package com.hoan.myapplication;

import android.content.Context;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyFn2 {
    public void getAllDataFromAPI(Context context, TextView textView) {
        // 1. Tạo hàng đợi request
        RequestQueue queue = Volley.newRequestQueue(context);

        // 2. URL
        String url = "https://hungnttg.github.io/array_json_new.json";

        // 3. Tạo request
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                StringBuilder strJSON = new StringBuilder();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject person = response.getJSONObject(i);
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String email = person.getString("email");
                        strJSON.append("Id: ").append(id).append("\n");
                        strJSON.append("Name: ").append(name).append("\n");
                        strJSON.append("Email: ").append(email).append("\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                textView.setText(strJSON.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
            }
        });

        // Thực thi request
        queue.add(request);
    }
}
