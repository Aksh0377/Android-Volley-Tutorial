package com.example.axay.volley_tutorial;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recyclectivity extends Activity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    private RecyclerView.Adapter adapter;
    private List<history_data.DataBean>  data;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclectivity);
        requestQueue= Volley.newRequestQueue(this);

        recyclerView=(RecyclerView)findViewById(R.id.recycelrview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data=new ArrayList<>();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.1.104/leave/public/api/leave/5"
                ,null,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject dataa = jsonArray.getJSONObject(i);

                        history_data.DataBean bean =new history_data.DataBean(

                                dataa.getString("type"),
                                dataa.getString("leave_reason"),
                                dataa.getString("from"),
                                dataa.getString("to"));


                           data.add(bean);
                    }

                    adapter= new Adapter(data,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplication(),"Data fetched successfully",Toast.LENGTH_LONG);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),"sorry no data fetched",Toast.LENGTH_LONG);

                Log.d("error", "error from server response");
            }}

        );


        requestQueue.add(jsonObjectRequest);


    }
}
