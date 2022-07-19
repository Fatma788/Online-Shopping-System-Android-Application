package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Check extends AppCompatActivity {
TextView Address,Total_Price;
Button logout;
Database database;
    int Final_Total_price;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Address=findViewById(R.id.addressshow);
        Total_Price=findViewById(R.id.price);
        logout=findViewById(R.id.button);
        database=new Database(this);
       // Intent intent = new Intent(Check.this,ShowCart.class);
         add=getIntent().getExtras().getString("Address");
        Address.setText(add.toString());
        Final_Total_price=0;
        for(int i=0;i<class_dbFn.cartItems.size();i++)
        {
            Final_Total_price+=class_dbFn.cartItems.get(i).TotalPrice;
        }
        Total_Price.setText(String.valueOf(Final_Total_price+"$"));
       // Intent intent2=new Intent();
        int custid=(int) getIntent().getExtras().getInt("customer_define",0);

        database.SaveOrder(add,currentDate,custid);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Check.this,Home.class);
                startActivity(intent);
            }
        });

    }
}