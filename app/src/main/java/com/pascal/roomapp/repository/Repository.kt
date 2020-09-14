package com.pascal.roomapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.pascal.roomapp.model.database.DatabaseConfig
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.model.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository(context: Context) {

    private var databaseConfig : DatabaseConfig? = null

    init {
        databaseConfig = DatabaseConfig.getInstance(context)
    }

    //Siswa
    @SuppressLint("CheckResult")
    fun showSiswa(responHandler: (List<Siswa>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.siswaDao()?.getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addSiswa(item: Siswa, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.siswaDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateSiswa(item: Siswa, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.siswaDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteSiswa(item: Siswa, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.siswaDao()?.delete(item!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }


    //User
    @SuppressLint("CheckResult")
    fun showUser(responHandler: (List<User>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addSiswaUser(item: User, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun getDataEmail(email: String, responHandler: (User) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.getDataEmail(email) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
                Log.d("TAG", " getUser$it")
            }, {
                errorHandler(it)
                Log.d("TAG", "getUser" + it.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun deleteUser(responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.delete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }
}