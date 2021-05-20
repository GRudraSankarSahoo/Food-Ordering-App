package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Menulist extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulist);
        recyclerView=findViewById(R.id.recyclarview);
        ArrayList<MainModel> list=new ArrayList<>();

        list.add(new MainModel(R.drawable.cappucino,"Cappucino","2"," A shot of espresso with textured milk poured immediately"));
        list.add(new MainModel(R.drawable.sandwich,"Sandwich","3","Two slice of bread with fresh meat and veggies with mayo in it"));
        list.add(new MainModel(R.drawable.samosa,"Samosa","1","Fresh smashed potatoes inside crispy layer"));
        list.add(new MainModel(R.drawable.ramen,"Ramen","10","Ramenis a noodle soup dish"));
        list.add(new MainModel(R.drawable.burger,"Burger","5","a sandwich consisting of one or more cooked patties of ground meat, usually beef"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","7","a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives, mozzarel"));
        list.add(new MainModel(R.drawable.salad,"Salad","3","a mixture of raw usually green leafy vegetables (as lettuce) combined with other vegetables "));
        list.add(new MainModel(R.drawable.biryani,"Biryani","11","Long-grained rice (like basmati) flavored with fragrant spices such as saffron and chicken"));
        list.add(new MainModel(R.drawable.fries,"Fries","4","French fries are pieces of potato that have been deep-fried."));
        list.add(new MainModel(R.drawable.thighs,"Thicc Thighs","100","Its finger licking good af"));

        MainAdapter adapter=new MainAdapter(list,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        auth=FirebaseAuth.getInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.topmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                startActivity(new Intent(Menulist.this,profileActivity.class));
                break;
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(Menulist.this,MainActivity.class));
                break;
            case R.id.orders:
                startActivity(new Intent(Menulist.this,OrderActivity.class));
                break;
        }

        return true;
    }
}