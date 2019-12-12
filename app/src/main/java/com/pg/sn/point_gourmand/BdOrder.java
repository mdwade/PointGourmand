package com.pg.sn.point_gourmand;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 19/05/2018.
 */

public class BdOrder extends SQLiteAssetHelper {

    public BdOrder(Context context){

        super(context,"order.db",null,1);
    }




    public void addOrder(String nom , String type , String quantity , String prix){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nameProd",nom);
            cv.put("produiType",type);
            cv.put("Quantity",quantity);
            cv.put("prix",prix);
            db.insert("orderDetail",null,cv);
            db.close();
        }
        catch (Exception e)
        {

        }
    }

    public void updateUser(String nom , String type , String quantity , String prix){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nameProd",nom);
            cv.put("produiType",type);
            cv.put("Quantity",quantity);
            cv.put("prix",prix);
            db.update("orderDetail",cv,"nameProd='"+nom+"'",null);
            db.close();

        }
        catch (Exception e)
        {

        }
    }

    public void deleteOrder(String login){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            db.delete("orderDetail","login='"+login+"'",null);
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public List<Order> getOrder() {
        List<Order> result = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"nameProd", "produiType", "Quantity", "prix"};
        String sqltabl = "orderDetail";
        qb.setTables(sqltabl);

        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        if (c.moveToFirst()) {

            do {
                String name = c.getString(c.getColumnIndex("nameProd"));
                String type = c.getString(c.getColumnIndex("produiType"));
                String quanta = c.getString(c.getColumnIndex("Quantity"));
                String p = c.getString(c.getColumnIndex("prix"));
                result.add(new Order(type, name, quanta, p));
            } while (c.moveToNext());


        }
        return result;
    }
}
