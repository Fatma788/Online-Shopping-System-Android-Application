package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reset extends AppCompatActivity {
    TextView username;
    EditText newpass;
    Button confirm;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username=findViewById(R.id.resetUsername);
        newpass=findViewById(R.id.resetpass);
        confirm=findViewById(R.id.confirm);
        db= new Database(getApplicationContext());
        Intent intent=getIntent();
        username.setText(intent.getStringExtra("username"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=newpass.getText().toString();
                Boolean checkpassupdate=db.updatepass(user,pass);
                if(checkpassupdate==true){
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Password Updated Successfully..",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Password Not Updated ",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}