package com.example.learnandroidjava.modal.store

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoStore(private val context: Context) {
    companion object {
        val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
        val USERNAME_KEY  = stringPreferencesKey("username")

        private val Context.userStore: DataStore<Preferences> by preferencesDataStore("user_store")
    }

    val isLogin: Flow<Boolean> = context.userStore.data.map { it[IS_LOGIN_KEY] ?: false }
    val username: Flow<String> = context.userStore.data.map { it[USERNAME_KEY] ?: "" }

    /**
     * 存储用户信息
     * 其实可以使用 datestore 的 对象存储，直接存储整个对象
     */
    suspend fun save(username: String) {
        context.userStore.edit {
            it[IS_LOGIN_KEY] = username.isNotEmpty()
            it[USERNAME_KEY] = username
        }
    }
}