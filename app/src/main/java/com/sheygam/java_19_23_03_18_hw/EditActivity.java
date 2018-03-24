package com.sheygam.java_19_23_03_18_hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputName, inputEmail, inputPhone;
    private Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String data = getIntent().getStringExtra("DATA");
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);

        if(getIntent().getAction().equals(getString(R.string.name_edit))){
            inputName.setText(data);
            inputName.setVisibility(View.VISIBLE);
        }else if(getIntent().getAction().equals(getString(R.string.email_edit))){
            inputEmail.setText(data);
            inputEmail.setVisibility(View.VISIBLE);
        }else if(getIntent().getAction().equals(getString(R.string.phone_edit))){
            inputPhone.setText(data);
            inputPhone.setVisibility(View.VISIBLE);
        }

        okBtn = findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ok_btn){
            String data = "";
            Intent intent = new Intent();
            if(getIntent().getAction().equals(getString(R.string.name_edit))){
                data = inputName.getText().toString();
            }else if(getIntent().getAction().equals(getString(R.string.email_edit))){
                data = inputEmail.getText().toString();
            }else if(getIntent().getAction().equals(getString(R.string.phone_edit))){
                data = inputPhone.getText().toString();
            }
            intent.putExtra("DATA",data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
