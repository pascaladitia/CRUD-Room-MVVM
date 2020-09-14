package com.pascal.roomapp.model.database

import androidx.room.*
import com.pascal.roomapp.model.Siswa

@Dao
interface DaoSiswa {

    @Query("SELECT * FROM siswa")
    fun getData(): List<Siswa>

    @Insert
    fun insert(siswa: Siswa)

    @Update
    fun update(siswa: Siswa)

    @Delete
    fun delete(siswa: Siswa)
}