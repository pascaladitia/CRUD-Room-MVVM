package com.pascal.roomapp.model.database

import androidx.room.*
import com.pascal.roomapp.model.User
import io.reactivex.Single

@Dao
interface DaoUser {

    @Query("SELECT * FROM user")
    fun getData(): List<User>

    @Query("SELECT * FROM user where email = :email")
    fun getDataEmail(email: String) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM user")
    fun delete()
}