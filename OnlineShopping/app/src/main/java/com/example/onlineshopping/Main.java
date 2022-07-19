package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
Button jumpsuit,dress,bottom,sweetshirt;
TextView textView;
Intent intent;
ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        jumpsuit=findViewById(R.id.jumsuit);
        dress=findViewById(R.id.dress);
        bottom=findViewById(R.id.bottoms);
        sweetshirt=findViewById(R.id.sweetshirt);
       // textView=findViewById(R.id.textView);



        jumpsuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",1);
                startActivity(intent);
            }
        });

      dress.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               intent=new Intent(Main.this,ProductShow.class);
               intent.putExtra("Cat_Id",2);
               startActivity(intent);
          }
      });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",3);
                startActivity(intent);
            }
        });
        sweetshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",4);
                startActivity(intent);
            }
        });


    }
}