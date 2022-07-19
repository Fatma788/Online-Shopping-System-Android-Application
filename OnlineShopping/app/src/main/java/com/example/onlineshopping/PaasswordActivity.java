package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaasswordActivity extends AppCompatActivity {
    EditText user;
    Button reset;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paassword);
        user=findViewById(R.id.editTextTextPersonName);
        reset=findViewById(R.id.Resert);
        db= new Database(getApplicationContext());
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=user.getText().toString();
                Boolean checkuser=db.checkUserName(username);
                if(checkuser==true){
                    Intent intent=new Intent(getApplicationContext(),Reset.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"User doesn't Exists",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}