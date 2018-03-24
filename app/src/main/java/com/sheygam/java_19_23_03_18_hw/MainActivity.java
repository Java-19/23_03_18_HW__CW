package com.sheygam.java_19_23_03_18_hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int EDIT_NAME = 0x01;
    private static final int EDIT_EMAIL = 0x02;
    private static final int EDIT_PHONE = 0x03;
    private TextView nameTxt, emailTxt, phoneTxt;
    private Button nameBtn, emailBtn, phoneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = findViewById(R.id.name_txt);
        emailTxt = findViewById(R.id.email_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        nameBtn = findViewById(R.id.name_btn);
        emailBtn = findViewById(R.id.email_btn);
        phoneBtn = findViewById(R.id.phone_btn);

        nameBtn.setOnClickListener(this);
        emailBtn.setOnClickListener(this);
        phoneBtn.setOnClickListener(this);

        load();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.name_btn:
                intent.setAction(getString(R.string.name_edit));
                intent.putExtra("DATA",nameTxt.getText());
                startActivityForResult(intent,EDIT_NAME);
                break;
            case R.id.email_btn:
                intent.setAction(getString(R.string.email_edit));
                intent.putExtra("DATA",emailTxt.getText());
                startActivityForResult(intent,EDIT_EMAIL);
                break;
            case R.id.phone_btn:
                intent.setAction(getString(R.string.phone_edit));
                intent.putExtra("DATA",phoneTxt.getText());
                startActivityForResult(intent,EDIT_PHONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case EDIT_NAME:
                    nameTxt.setText(data.getStringExtra("DATA"));
                    break;
                case EDIT_EMAIL:
                    emailTxt.setText(data.getStringExtra("DATA"));
                    break;
                case EDIT_PHONE:
                    phoneTxt.setText(data.getStringExtra("DATA"));
                    break;
            }
        }
    }

    private void save(){
        String str = "Hello";
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("STR",str);
        editor.commit();
    }

    private void load(){
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        String str = sharedPreferences.getString("STR","empty");
        nameTxt.setText(str);
    }

    @Override
    protected void onDestroy() {
        save();
        super.onDestroy();
    }
}
