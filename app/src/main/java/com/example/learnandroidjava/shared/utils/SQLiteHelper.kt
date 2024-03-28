package com.example.learnandroidjava.shared.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
) : SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        private var instant: SQLiteOpenHelper? = null;

        @Synchronized
        fun getInstant(context: Context?): SQLiteOpenHelper? {
            if (instant == null) {
                instant =  SQLiteHelper(context, "mini_ocean.db", null, 3)
            }
            return instant
        }
    }

    // 数据库第一次创建的时候使用
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "create table user(_id integer primary key autoincrement, name varchar(20), password varchar(20))"
        db?.execSQL(sql)
    }

    // 数据库升级时候使用
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}