package com.example.axay.volley_tutorial;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class listview_test extends AppCompatActivity {

    Button  Edit_Rec;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_test);

        Edit_Rec=(Button)findViewById(R.id.edit_recepients);



        final FragmentManager fm=getFragmentManager();
        final  Edit_recepients er=new Edit_recepients();
        Edit_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        er.show(fm,"TV_tag");

            }
        });



                if(getIntent().getExtras()!=null) {
            Bundle b = getIntent().getExtras();
            String[] resultArr = b.getStringArray("selectedItems");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, resultArr);
            lv.setAdapter(adapter);
        }


    }


}
