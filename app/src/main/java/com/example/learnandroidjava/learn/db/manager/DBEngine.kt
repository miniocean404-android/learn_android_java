package com.example.learnandroidjava.learn.db.manager

import android.content.Context
import android.util.Log
import com.example.learnandroidjava.learn.db.dao.StudentDao
import com.example.learnandroidjava.learn.db.database.StudentDatabase
import com.example.learnandroidjava.learn.db.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch
class DBEngine(private val context: Context) {
    private val TAG: String? = DBEngine::class.simpleName
    private var studentDao: StudentDao? = StudentDatabase.getInstant(context)?.getStudentDao()

    fun insert(vararg students: Student) {
        CoroutineScope(Dispatchers.Default).launch {
            // 后台线程中执行插入操作
            insertStudents(*students)
        }
    }

    fun delete(vararg students: Student) {
        CoroutineScope(Dispatchers.Default).launch {
            // 后台线程中执行插入操作
            deleteStudents(*students)
        }
    }


    fun deleteAll(vararg students: Student) {
        CoroutineScope(Dispatchers.Default).launch {
            // 后台线程中执行插入操作
            deleteAllStudents()
        }
    }

    fun update(vararg students: Student) {
        CoroutineScope(Dispatchers.Default).launch {
            // 后台线程中执行插入操作
            updateStudents(*students)
        }
    }

    fun queryAll() {
        CoroutineScope(Dispatchers.Default).launch {
            // 后台线程中执行插入操作
            queryAllStudents()
        }
    }


    private suspend fun insertStudents(vararg students: Student) {
        // 执行插入学生数据的逻辑
        studentDao?.insert(*students)
    }

    private suspend fun deleteStudents(vararg students: Student) {
        studentDao?.delete(*students)
    }


    private suspend fun deleteAllStudents() {
        studentDao?.deleteAll()
    }

    private suspend fun updateStudents(vararg students: Student) {
        // 执行插入学生数据的逻辑
        studentDao?.update(*students)
    }

    private suspend fun queryAllStudents() {
        // 执行插入学生数据的逻辑
        val students = studentDao?.queryAll()

        for (student in students!!) {
            Log.i(TAG, "queryAllStudents: ${student.id} ${student.name} ${student.age} ${student.id}")
        }
    }
}