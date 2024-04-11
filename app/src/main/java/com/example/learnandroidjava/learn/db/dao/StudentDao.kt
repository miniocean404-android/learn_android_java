package com.example.learnandroidjava.learn.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.learnandroidjava.learn.db.entity.Student

@Dao
interface StudentDao {
    @Insert
    fun insert(vararg students: Student)

    @Update
    fun update(vararg students: Student)

    @Delete
    fun delete(vararg students: Student)

    /**
     * @Delete 单个删除用的
     */
    @Query("Delete FROM Student")
    fun deleteAll()


    @Query("SELECT * FROM Student Order By _id DESC")
    fun queryAll(): List<Student>
}