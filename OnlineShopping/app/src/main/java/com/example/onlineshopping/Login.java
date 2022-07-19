package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Login extends AppCompatActivity {
    EditText name,password;
    Button login;
    Database db;
    CheckBox remember;
    TextView forgot;
    SharedPreferences mprefs;
    private String prefs_Name="Prefs_File";
    class_customer customer;
    class_order order;
    FragmentManager fragmentManager;
    private Fragment mmapfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        name=findViewById(R.id.Name);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login3);
        remember=findViewById(R.id.rem);
        mprefs=getSharedPreferences(prefs_Name,MODE_PRIVATE);
        db=new Database(this);
        forgot=findViewById(R.id.forgotpass);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PaasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    if (db.checkUserName_Pass(user, pass)) {
                        if(remember.isChecked()){
                            boolean isCheck=remember.isChecked();
                            SharedPreferences.Editor editor= mprefs.edit();
                            editor.putString("pref_name",user);
                            editor.putString("pref_pass",pass);
                            editor.putBoolean("pref_check",isCheck);
                            editor.apply();
                            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                        }else{
                            mprefs.edit().clear().apply();
                        }
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),Main.class);
                        customer=new class_customer();
                        int id=customer.CustID;
                        order=new class_order(customer.CustID,customer.CustID,currentDate);
                        Intent intent1=new Intent(Login.this,Product_details.class);
                        Intent intent2=new Intent(Login.this,Check.class);
                        intent2.putExtra("customer_define",id);
                        intent1.putExtra("Order_id",order.OrdID);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();

                }

            }
        });
        getPreferencesData();
    }
    private void getPreferencesData() {
        SharedPreferences sp=getSharedPreferences(prefs_Name,MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u=sp.getString("pref_name","not found");
            name.setText(u.toString());
        }
        if(sp.contains("pref_pass")){
            String p=sp.getString("pref_pass","not found");
            password.setText(p.toString());
        }
        if(sp.contains("pref_check")){
            Boolean b=sp.getBoolean("pref_check",false);
            remember.setChecked(b);
        }

    }
}