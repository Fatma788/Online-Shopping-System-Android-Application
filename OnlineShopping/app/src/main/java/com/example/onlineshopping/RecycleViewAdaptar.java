package com.example.onlineshopping;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdaptar extends RecyclerView.Adapter<RecycleViewAdaptar.ProjectHolder> {

   ArrayList<products>myproducts;
   ArrayList<products>products;
   int id;
   Context context;
   products product;
  // int postion;
    public class ProjectHolder  extends  RecyclerView.ViewHolder{
        public ImageView MyProduct;
        public TextView ProductName;
        public TextView ProductDescription;
        ImageButton imageButton;
        //final TextView total = findViewById(R.id.total);

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            MyProduct=itemView.findViewById(R.id.cproimage);
            ProductName=itemView.findViewById(R.id.productname);
            ProductDescription=itemView.findViewById(R.id.productdescription);
            imageButton=itemView.findViewById(R.id.imageView);
             products=new ArrayList<products>();
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int pos=getBindingAdapterPosition();
                products product=myproducts.get(pos);
                    Intent intent=new Intent(itemView.getContext(),Product_details.class);
                    intent.putExtra("Pro_id",product.getPro_id());
                    intent.putExtra("Pro_image",product.getProimage());
                    intent.putExtra("Pro_Name",product.getProName());
                    intent.putExtra("Pro_Price",product.getPrice());
                    intent.putExtra("Pro_Quantity",product.getPro_quantity());
                    context.startActivity(intent);
                }
            });
        }
    }

    public  RecycleViewAdaptar (ArrayList<products>products)
    {
        myproducts=products;
    }
    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false);
    ProjectHolder projectHolder=new ProjectHolder(view);
        return projectHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {
            products product=myproducts.get(position);
            holder.MyProduct.setImageResource(product.getProimage());
            holder.ProductName.setText(product.getProName());
            holder.ProductDescription.setText(String.valueOf((int) product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return myproducts.size();
    }

}
