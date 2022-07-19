package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.Parser;

public class Product_details extends AppCompatActivity {
ImageView Pro_image;
TextView Pro_name,Pro_price;
EditText Quentity;
int count;
Button Add,add_quentity,sub_quentuty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Pro_image=findViewById(R.id.proimage);
        Pro_name=findViewById(R.id.proname);
        Pro_price=findViewById(R.id.proprice);
        Quentity=findViewById(R.id.quentity);
        add_quentity=findViewById(R.id.addQuantity);
        sub_quentuty=findViewById(R.id.subQuantity);
        Add=findViewById(R.id.addtocart);
        count= Integer.parseInt(Quentity.getText().toString());
        int proim=getIntent().getIntExtra("Pro_image",0);
        Pro_image.setImageResource(proim);
        String proname=getIntent().getStringExtra("Pro_Name");
        Pro_name.setText(proname);
        double price=getIntent().getDoubleExtra("Pro_Price",0);
        Pro_price.setText(String.valueOf(price));
      //  int proq=getIntent().getIntExtra("Pro_Quantity",0);
       // Pro_image.setImageResource(proq);
        add_quentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Quentity.setText(String.valueOf(count));
            }
        });
      sub_quentuty.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              count--;
              Quentity.setText(String.valueOf(count));
          }
      });

      Add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              int Total_price= (int) (price*count);
              int orderid= getIntent().getIntExtra("Order_id",0);
              int proid=getIntent().getIntExtra("Pro_id",0);
              class_dbFn.cartItems.add(new class_orderDetails(orderid,proid,count,Total_price,proname,price));
          }
      });
    }
}