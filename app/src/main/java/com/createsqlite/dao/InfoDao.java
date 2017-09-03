package com.createsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.createsqlite.bean.InfoBean;
import com.createsqlite.db.MySQLiteOpenHelper;

/**
 * Created by hejiao on 2017/9/1.
 */

public class InfoDao {

    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public InfoDao(Context context) {

        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);

    }
/*
    public void add(InfoBean bean) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        //sql:sql语句，  bindArgs：sql语句中占位符的值
        db.execSQL("insert into info(name,phone) values(?,?);", new Object[]{bean.name, bean.phone});

        db.close();
    }

    public void del(String name) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        db.execSQL("delete from info where name=?;", new Object[]{name});

        db.close();
    }

    public void update(InfoBean bean) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        db.execSQL("update info set phone=?where name=?;", new Object[]{bean.name, bean.phone});

        db.close();
    }

    public void query(String name) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select _id,name,phone from info where name=?;", new String[]{name});

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //获取数据
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);

                Log.e("Cursor::", "id:" + id + ";name:" + name_str + ";phone" + phone);
            }
            cursor.close();
        }

        db.close();
    }
*/

    public boolean add(InfoBean bean) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put("name",bean.name);
        contentValues.put("phone",bean.phone);

        //table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
        long result= db.insert("info",null,contentValues);

        db.close();

        if(result!= -1){
            return true;
        }else {
            return false;
        }
    }

    public int del(String name) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        //table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
        int result= db.delete("info","name= ?",new String[]{name});

        db.close();

        return result;
    }

    public int update(InfoBean bean) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        ContentValues values= new ContentValues();
        values.put("phone",bean.phone);

        //table:表名, values：更新的值, whereClause:更新的条件, whereArgs：更新条件的占位符的值,返回值：成功修改多少行
        int result= db.update("info",values,"name= ?",new String[]{bean.name});

        db.close();

        return result;
    }

    public void query(String name) {

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        //table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
        //groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor= db.query("info",new String[]{"_id","name","phone"},"name= ?",new String[]{name},null,null,"_id desc");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //获取数据
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);

                Log.e("Cursor::", "id:" + id + ";name:" + name_str + ";phone" + phone);
            }
            cursor.close();
        }

        db.close();
    }
}
