package com.pascal.roomapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.repository.Repository

class ViewModelSiswa(application: Application) : AndroidViewModel(application) {

    val repository = Repository(application.applicationContext)

    var responShowSiswa = MutableLiveData<List<Siswa>?>()
    var isError = MutableLiveData<Throwable>()
    var responAddSiswa = MutableLiveData<Unit>()
    var responUpdateSiswa = MutableLiveData<Unit>()
    var responseDeleteSiswa = MutableLiveData<Unit>()

    fun showSiswaView() {
        repository.showSiswa({
            responShowSiswa.value = it
        }, {
            isError.value = it
        })
    }

    fun addSiswaView(item: Siswa) {
        repository.addSiswa(item, {
            responAddSiswa.value = it
        }, {
            isError.value = it
        })
    }

    fun updateSiswaView(item: Siswa) {
        repository.updateSiswa(item, {
            responUpdateSiswa.value = it
        }, {
            isError.value = it
        })
    }

    fun deleteSiswaView(item: Siswa) {
        repository.deleteSiswa(item, {
            responseDeleteSiswa.value = it
        }, {
            isError.value = it
        })
    }
}