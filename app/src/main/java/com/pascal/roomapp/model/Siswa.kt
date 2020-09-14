package com.pascal.roomapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "siswa")
data class Siswa (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "nohp")
    var nohp: String? = null,

    @ColumnInfo(name = "alamat")
    var alamat: String? = null
)