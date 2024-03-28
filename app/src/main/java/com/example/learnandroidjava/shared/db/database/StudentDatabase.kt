package com.example.learnandroidjava.shared.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learnandroidjava.shared.db.dao.StudentDao
import com.example.learnandroidjava.shared.db.entity.Student

@Database(entities = [Student::class], version = 3, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {
        private var instant: StudentDatabase? = null

        @Synchronized
        fun getInstant(context: Context): StudentDatabase? {
            if (instant == null) {
                instant = Room.databaseBuilder(
                    context,
                    StudentDatabase::class.java,
                    "student"
                )
                    // 数据库默认是异步
                    // allowMainThreadQueries 可以改成主线程同步，但是不推荐，测试可以用
                    // .allowMainThreadQueries()
                    .build()
            }

            return instant
        }
    }
}