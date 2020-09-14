package com.pascal.roomapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.model.User

@Database(entities = arrayOf(Siswa::class, User::class), version = 2, exportSchema = false)
abstract class DatabaseConfig : RoomDatabase() {

    abstract fun siswaDao(): DaoSiswa
    abstract fun userDao(): DaoUser

    companion object {
        private var INSTANCE: DatabaseConfig? = null

        fun getInstance(context: Context): DatabaseConfig? {
            if (INSTANCE == null) {
                synchronized(DatabaseConfig::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseConfig::class.java, "dbsiswa.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}