package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME="mydatabase.db";
    final static int DBVERSION=2;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders"+
                        "(id integer primary key autoincrement,"+
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertorder(String name,String phone,int price,int image,String desc,String foodName,int quantity){
         SQLiteDatabase database=getReadableDatabase();
        ContentValues values=new ContentValues();

        /*
        id=0
        name=1
        phone=2
        price=3
        image=4
        quantity=5
        description=6
        foodneame=7

         */

        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description",desc);
        values.put("foodname",foodName);

        long id=database.insert("orders",null,values);
        if(id<=0)
            return false;
        else
            return true;
    }

    public ArrayList<OdersModel> getOrders(){
        ArrayList<OdersModel> orders=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OdersModel models=new OdersModel();
                models.setOrderNumber(cursor.getInt(0)+"");
                models.setOrderName(cursor.getString(1));
                models.setOrderImage(cursor.getInt(2));
                models.setOrderPrice(cursor.getInt(3)+"");
                orders.add(models);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("Select * from orders where id=" +id,null);

        if(cursor!=null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOrder(String name,String phone,int price,int image,String desc,String foodName,int quantity,int id){
        SQLiteDatabase database=getReadableDatabase();
        ContentValues values=new ContentValues();

        /*
        id=0
        name=1
        phone=2
        price=3
        image=4
        quantity=5
        description=6
        foodneame=7

         */

        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description",desc);
        values.put("foodname",foodName);

        long row=database.update("orders",values,"id="+id,null);
        if(row<=0)
            return false;
        else
            return true;
    }

    public int deleteOrder(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }
}
