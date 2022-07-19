package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowCart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter MyAdapter;
    private RecyclerView.LayoutManager MyManager;
    Button check;
    double Final_Total_price;
    EditText address;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_cart);
        recyclerView=findViewById(R.id.cartview);
        check=findViewById(R.id.finish);
        MyManager=new LinearLayoutManager(this);
        MyManager=new LinearLayoutManager(getApplicationContext());
        MyAdapter=new CartAdapter(class_dbFn.cartItems);
        recyclerView.setLayoutManager(MyManager);
        recyclerView.setAdapter(MyAdapter);
        address=findViewById(R.id.address);
         add=address.getText().toString();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowCart.this,Check.class);
                intent.putExtra("Address",address.getText().toString());
                startActivity(intent);
            }
        });

    }
}