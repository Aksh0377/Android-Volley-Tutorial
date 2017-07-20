package com.example.axay.volley_tutorial;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {


    public  String ERROR_TAG="EEEOR";

    EditText f_name,pass,e_mail,designation,t_eam,dept;
    Button register,select_img;
    RequestQueue requestQueue;

    public static  final String KEY_USERNAME="first_name";
    public static  final String KEY_PASSWORD="user_password";
    public static  final String KEY_EMAIL="email";
    public static  final String KEY_DESIGNATION="user_designation";
    public static  final String AVTAR="avatar";

    public static  final String KEY_TEAM="team";
    public static  final String KEY_DEPARTMENT="department";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        f_name=(EditText)findViewById(R.id.f_name);
        pass=(EditText)findViewById(R.id.password);
        e_mail=(EditText)findViewById(R.id.email);
        designation=(EditText)findViewById(R.id.designation);
        t_eam=(EditText)findViewById(R.id.team);
        dept=(EditText)findViewById(R.id.department);
        requestQueue= Volley.newRequestQueue(this);
        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first_name= f_name.getText().toString().trim();
                final String user_password= pass.getText().toString().trim();
                final String email= e_mail.getText().toString().trim();
                final String user_designation =designation.getText().toString().trim();
                final String avatar ="";
                final String team= t_eam.getText().toString().trim();
                final String department= dept.getText().toString().trim();

                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, first_name);
                params.put(KEY_PASSWORD, user_password);
                params.put(KEY_EMAIL, email);
                params.put(KEY_DESIGNATION, user_designation);
                params.put(AVTAR,avatar);
                params.put(KEY_TEAM, String.valueOf(team));
                params.put(KEY_DEPARTMENT, String.valueOf(department));
                JSONObject json = new JSONObject(params);
                System.out.println(json);
                Log.d(ERROR_TAG,json.toString());


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.104/leave/public/api/users"
                   , json,new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                           // JSONArray jsonArray=new JSONArray(response);
                            Log.d("ds",response.toString());
                            Toast.makeText(Register.this,response.toString(),Toast.LENGTH_LONG).show();
                           // JSONObject jsonObject=jsonArray.getJSONObject(0);
                            //String Code= jsonObject.getString("code");
                            //String message= jsonObject.getString("message");
                            //Toast.makeText(Register.this,Code,Toast.LENGTH_LONG).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(Register.this,response.toString(),Toast.LENGTH_LONG).show();

                        Log.d(ERROR_TAG,"error");

                    }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                             error.printStackTrace();
                        Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("error", "error from server response");
                    }});





                        requestQueue.add(jsonObjectRequest);


            }


        });



    }
}
