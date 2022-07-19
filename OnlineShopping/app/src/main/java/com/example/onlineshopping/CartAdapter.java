package com.example.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProjectHolder>  {

    ArrayList<class_orderDetails> cartitems;

    public CartAdapter(ArrayList<class_orderDetails> cartitems) {
        this.cartitems = cartitems;
    }
    public class ProjectHolder  extends  RecyclerView.ViewHolder{
        public TextView ProductName;
        public TextView Price;
        public TextView Total_Price;
        EditText editquentity;
        Button Edit,Remove;
        public ProjectHolder(@NonNull View itemView)
        {
            super(itemView);
            ProductName=itemView.findViewById(R.id.cproname);
            Price=itemView.findViewById(R.id.cproprice);
            Total_Price=itemView.findViewById(R.id.cprototal);
            Edit=itemView.findViewById(R.id.edit);
            Remove=itemView.findViewById(R.id.remove);
            editquentity=itemView.findViewById(R.id.editquentity);
            ProjectHolder holder=this;
            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getBindingAdapterPosition();
                    class_orderDetails orderDetails=cartitems.get(pos);
                    cartitems.get(pos).Quantity= Integer.parseInt(editquentity.getText().toString());
                    int total_price_final= (int) (cartitems.get(pos).Price*Integer.parseInt(editquentity.getText().toString()));
                    cartitems.get(pos).TotalPrice=total_price_final;
                    notifyItemRangeChanged(pos, cartitems.size());

                }
            });
            Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getBindingAdapterPosition();
                    removeItem(holder,pos);

                }
            });
        }

    }
    private void removeItem(ProjectHolder holder,int position) {
        int newPosition = holder.getBindingAdapterPosition();
        cartitems.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, cartitems.size());
    }

    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartdesign,parent,false);
        CartAdapter.ProjectHolder projectHolder=new CartAdapter.ProjectHolder(view);
        return projectHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {
        class_orderDetails orderDetails=cartitems.get(position);
        holder.ProductName.setText(orderDetails.ProName);
        holder.Price.setText(String.valueOf((int) orderDetails.Price));
        holder.Total_Price.setText(String.valueOf( orderDetails.TotalPrice));
    }

    @Override
    public int getItemCount() {
        return cartitems.size();
    }


}