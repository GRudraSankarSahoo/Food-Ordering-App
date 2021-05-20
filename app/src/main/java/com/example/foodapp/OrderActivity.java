package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    RecyclerView orderRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderRecyclerView=findViewById(R.id.orderRecyclerView);
        DBHelper helper=new DBHelper(this);
        ArrayList<OdersModel> list=helper.getOrders();

        OrdersAdpater adpater=new OrdersAdpater(list,this);
        orderRecyclerView.setAdapter(adpater);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        orderRecyclerView.setLayoutManager(layoutManager);
    }
}