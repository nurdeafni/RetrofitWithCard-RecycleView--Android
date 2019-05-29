package com.nurde.retrofitwithcardandrecycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputData extends AppCompatActivity {
    private Button btn_save;
    private EditText inputNama,inputEmail,inputAlamat,inputNohp;
    public static final String EXTRA_NAME= "com.nurde.retrofitwithcardandrecycleview.EXTRA_NAME";
    public static final String EXTRA_EMAIL= "com.nurde.retrofitwithcardandrecycleview.EXTRA_EMAIL";
    public static final String EXTRA_ADDRESS= "com.nurde.retrofitwithcardandrecycleview.EXTRA_ADDRESS";
    public static final String EXTRA_PHONE= "com.nurde.retrofitwithcardandrecycleview.EXTRA_PHONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        btn_save = (Button) findViewById(R.id.btn_save);

        inputNama = (EditText) findViewById(R.id.txt_nama);
        inputEmail = (EditText) findViewById(R.id.txt_email);
        inputAlamat = (EditText) findViewById(R.id.txt_alamat);
        inputNohp = (EditText) findViewById(R.id.txt_nohp);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveContact();
            }
        });

    }
    public void SaveContact(){
        String name =inputNama.getText().toString();
        String email = inputEmail.getText().toString();
        String alamat = inputAlamat.getText().toString();
        String noHp = inputNohp.getText().toString();

        if(name.trim().isEmpty() || noHp.trim().isEmpty()){
            Toast.makeText(this,"Please insert name and phone",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_EMAIL,email);
        data.putExtra(EXTRA_ADDRESS,alamat);
        data.putExtra(EXTRA_PHONE,noHp);

        setResult(RESULT_OK,data);
        finish();

    }
}
