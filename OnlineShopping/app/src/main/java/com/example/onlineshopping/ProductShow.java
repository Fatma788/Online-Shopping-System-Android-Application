package com.example.onlineshopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ProductShow extends AppCompatActivity   {
    TextView voice_search;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter MyAdapter;
    private RecyclerView.LayoutManager MyManager;
    ArrayList<products> product;
    private final int camera_Request=1888;
    String result1;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show);
        recyclerView=findViewById(R.id.recycleview);
        voice_search=findViewById(R.id.voice2);
        database=new Database(this);
        product=new ArrayList<products>();
        MyManager=new LinearLayoutManager(this);
        int level = getIntent().getIntExtra("Cat_Id", 0);
        product=database.GetProductWithCat(level);
        MyManager=new LinearLayoutManager(getApplicationContext());
        MyAdapter=new RecycleViewAdaptar(product);
        recyclerView.setLayoutManager(MyManager);
        recyclerView.setAdapter(MyAdapter);
        voice_search.setText(result1);
    }
    public  void getSpeechInput(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK && data !=null){

                    ArrayList<String> result=  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    result1 = result.get(0);
                    voice_search.setText(result1);
                    Toast.makeText(getApplicationContext(),result1,Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        return super.onCreateOptionsMenu(menu);
    }
    public void searchvoice(){
        String s=voice_search.getText().toString();
        s=s.toLowerCase();
        ArrayList<products>newlist=new ArrayList<>();
        for(products pro:product){
            String name=pro.getProName().toLowerCase();
            if (name.contains(s)){
                newlist.add(pro);
            }
        }
        MyManager=new LinearLayoutManager(getApplicationContext());
        MyAdapter=new RecycleViewAdaptar(newlist);
        recyclerView.setLayoutManager(MyManager);
        recyclerView.setAdapter(MyAdapter);
        //voice_search.setText("");
    }

    public void search(MenuItem item){
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toLowerCase();
                ArrayList<products>newlist=new ArrayList<>();
                for(products pro:product){
                    String name=pro.getProName().toLowerCase();
                    if (name.contains(newText)){
                        newlist.add(pro);
                    }
                }
                MyManager=new LinearLayoutManager(getApplicationContext());
                MyAdapter=new RecycleViewAdaptar(newlist);
                recyclerView.setLayoutManager(MyManager);
                recyclerView.setAdapter(MyAdapter);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                search(item);
                return true;
            case R.id.camera:
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent,camera_Request);
                return true;
            case R.id.voice:
                getSpeechInput();
                searchvoice();
                return  true;
            case R.id.imageButton:
                Intent intent=new Intent(ProductShow.this,ShowCart.class);
                startActivity(intent);
                return  true;
        }
        return false;
    }
}
