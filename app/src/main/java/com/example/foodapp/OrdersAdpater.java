package com.example.foodapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersAdpater extends RecyclerView.Adapter<OrdersAdpater.viewHolder> {

    ArrayList<OdersModel> list;
    Context context;

    public OrdersAdpater(ArrayList<OdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        OdersModel model=list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.orderName.setText(model.getOrderName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.orderPrice.setText(model.getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setMessage("Are you sure to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper helper=new DBHelper(context);
                                if(helper.deleteOrder(model.getOrderNumber())>0)
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView orderName,orderPrice,orderNumber;
        ImageView orderImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderName=itemView.findViewById(R.id.orderName);
            orderPrice=itemView.findViewById(R.id.orderPrice);
            orderNumber=itemView.findViewById(R.id.orderNumber);
            orderImage=itemView.findViewById(R.id.orderImage);
        }
    }
}
