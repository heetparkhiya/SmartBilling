package com.example.smartbilling.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.smartbilling.Bean.Bean_ProductItems;
import com.example.smartbilling.DBConstant.DB_CONSTANT;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DB_ProductList extends SQLiteAssetHelper {
    public DB_ProductList(Context context) {
        super(context, DB_CONSTANT.DB_NAME, null, DB_CONSTANT.DB_VERSION);
    }

    public void InsertProduct(Bean_ProductItems add) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("DesignNo", add.getDesignNo());
        insertValues.put("Size", add.getSize());
        insertValues.put("Rate", "NULL");
        Log.e("added",add.getSizeWiseQty());
        insertValues.put("SizeWiseQty", add.getSizeWiseQty());
        insertValues.put("Qty", "NULL");
        insertValues.put("Amount", add.getAmount());
        insertValues.put("RateSize", add.getRateSize());
        insertValues.put("MRP_Size", add.getMRP_Size());
        db.insert("MST_ProductItems", null, insertValues);
        db.close();
    }

    public ArrayList<Bean_ProductItems> SelectAll() {
        ArrayList<Bean_ProductItems> arrayList = new ArrayList<Bean_ProductItems>();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select * from MST_ProductItems";
        Cursor cursor = db.rawQuery(strQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bean_ProductItems Product = new Bean_ProductItems();
                Product.setItemID(cursor.getInt(cursor.getColumnIndex("ItemID")));
                Product.setDesignNo(cursor.getString(cursor.getColumnIndex("DesignNo")));
                Product.setSize(cursor.getString(cursor.getColumnIndex("Size")));
                Product.setRate(cursor.getString(cursor.getColumnIndex("Rate")));
                Product.setQty(cursor.getString(cursor.getColumnIndex("Qty")));
                Product.setAmount(cursor.getString(cursor.getColumnIndex("Amount")));
                Product.setSizeWiseQty(cursor.getString(cursor.getColumnIndex("SizeWiseQty")));
                arrayList.add(Product);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public int getAmount() {
        int sum = 0;
        SQLiteDatabase db = getWritableDatabase();
        String sumQuery = "SELECT SUM(Amount) From MST_ProductItems";
        Cursor cu = db.rawQuery(sumQuery, null);
        if (cu.moveToFirst())
            sum = cu.getInt(cu.getColumnIndex("SUM(Amount)"));
        return sum;
    }

    public int getQuantity() {
        int sum = 0;
        SQLiteDatabase db = getWritableDatabase();
        String sumQuery = "SELECT SUM(Qty) From MST_ProductItems";
        Cursor cu = db.rawQuery(sumQuery, null);
        if (cu.moveToFirst())
            sum = cu.getInt(cu.getColumnIndex("SUM(Qty)"));
        return sum;
    }

    public void Delete() {
        SQLiteDatabase db = getWritableDatabase();
        String strQuery = "Delete from MST_ProductItems";
        db.execSQL(strQuery);
        db.close();
    }

   /* @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        oldVersion = 1;
        newVersion = 2;
        super.onUpgrade(db, oldVersion, newVersion);
    }*/
}