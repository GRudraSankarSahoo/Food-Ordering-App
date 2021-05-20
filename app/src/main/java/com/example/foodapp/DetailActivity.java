package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImage,add,minus;
    TextView detailName,detailDescription,detailPrice,quantity;
    EditText nameBox,phoneBox;
    Button orderBtn;
    LinearLayout parent;
    int count=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().hide();
        DBHelper helper = new DBHelper(this);
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailDescription = findViewById(R.id.detailDescription);
        detailPrice = findViewById(R.id.detailPrice);
        nameBox = findViewById(R.id.nameBox);
        phoneBox = findViewById(R.id.phoneBox);
        orderBtn = findViewById(R.id.orderBtn);
        quantity = findViewById(R.id.quantity);
        parent=findViewById(R.id.parent);
        add=findViewById(R.id.add);
        minus=findViewById(R.id.minus);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=15) {
                    count++;
                }
                quantity.setText(String.valueOf(count));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=1){
                    count--;
                }
                quantity.setText(String.valueOf(count));
            }
        });


        if(getIntent().getIntExtra("type",0)==1) {




            int image = getIntent().getIntExtra("Image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("desc");

            detailImage.setImageResource(image);
            detailPrice.setText(String.format("%d", price));
            detailName.setText(name);
            detailDescription.setText(description);

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertorder(nameBox.getText().toString(), phoneBox.getText().toString(), price, image, description, name, Integer.parseInt(quantity.getText().toString()));
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Data success", Toast.LENGTH_SHORT).show();
                        Snackbar.make(parent,"Order Successful",Snackbar.LENGTH_SHORT)
                                .setAction("Go to cart", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(DetailActivity.this,OrderActivity.class));
                                    }
                                }).show();
                    }
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);
            Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            int image=cursor.getInt(4);
            detailImage.setImageResource(image);
            detailPrice.setText(String.format("%d", cursor.getInt(3)));
            detailName.setText(cursor.getString(7));
            detailDescription.setText(cursor.getString(6));

            nameBox.setText(cursor.getString(1));
            phoneBox.setText(cursor.getString(2));
            orderBtn.setText("Updtae Now");

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated=helper.updateOrder(
                            nameBox.getText().toString(),
                            phoneBox.getText().toString(),
                            Integer.parseInt(detailPrice.getText().toString()),
                            image,
                            detailDescription.getText().toString(),
                            detailName.getText().toString(),
                            1,
                            id
                    );
                    if(isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}