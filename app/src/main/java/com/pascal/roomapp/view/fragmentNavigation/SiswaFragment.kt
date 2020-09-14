package com.pascal.roomapp.view.fragmentNavigation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.roomapp.adapter.SiswaAdapter
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.viewModel.ViewModelSiswa
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.fragment_note.*

class SiswaFragment : Fragment() {

    private lateinit var viewModel : ViewModelSiswa
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelSiswa::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        attachObserve()
        viewModel.showSiswaView()

        btn_add.setOnClickListener {
            navController.navigate(R.id.action_noteFragment_to_inputFragment)
        }
    }

    private fun attachObserve() {
        viewModel.responShowSiswa.observe(viewLifecycleOwner, Observer {showSiswaView(it)})
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.responseDeleteSiswa.observe(viewLifecycleOwner, Observer { showDeleteSiswa(it) })
    }

    private fun showSiswaView(it: List<Siswa>?) {
        recycler_siswa.adapter = SiswaAdapter(it, object : SiswaAdapter.OnClickListener {

            override fun update(item: Siswa?) {
                val bundle = bundleOf(
                    "id" to item?.id,
                    "nama" to item?.nama,
                    "nohp" to item?.nohp,
                    "alamat" to item?.alamat
                )
                navController.navigate(
                    R.id.action_noteFragment_to_inputFragment, bundle)
            }

            override fun delete(item: Siswa?) {
                AlertDialog.Builder(context).apply {
                    setTitle("Hapus")
                    setMessage("Yakin Hapus Siswa?")
                    setCancelable(false)

                    setPositiveButton("Yes") {dialogInterface, i ->
                        viewModel.deleteSiswaView(item!!)
                    }
                    setNegativeButton("Cancel") {dialogInterface, i ->
                        dialogInterface?.dismiss()
                    }
                }.show()
            }

        })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showDeleteSiswa(it: Unit?) {
        Toast.makeText(context, "Siswa berhasil dihapus", Toast.LENGTH_SHORT).show()
        viewModel.showSiswaView()
    }
}