package com.pascal.roomapp.view.fragmentNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pascal.roomapp.model.Siswa
import com.pascal.roomapp.viewModel.ViewModelSiswa
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.fragment_input.*

class InputFragment : Fragment() {

    private lateinit var viewModel : ViewModelSiswa
    private var item : Siswa? = null
    var getId : Int? = null
    var getName : String? = null
    var getNohp : String? = null
    var getAlamat : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelSiswa::class.java)

        getId = arguments?.getInt("id")
        getName = arguments?.getString("nama")
        getNohp = arguments?.getString("nohp")
        getAlamat = arguments?.getString("alamat")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachObserve()
        inputCofig()
    }

    private fun inputCofig() {
        if (getName != null) {
            input_name.setText(getName)
            input_nohp.setText(getNohp)
            input_alamat.setText(getAlamat)

            btn_Input.text = "Update"
        }

        when (btn_Input.text) {
            "Update" -> {
                btn_Input.setOnClickListener {
                    viewModel.updateSiswaView(
                        Siswa(
                            getId, input_name.text.toString(),
                            input_nohp.text.toString(),
                            input_alamat.text.toString()))
                }
            }
            else -> {
                btn_Input.setOnClickListener {
                    viewModel.addSiswaView(
                        Siswa(
                            null, input_name.text.toString(),
                            input_nohp.text.toString(),
                            input_alamat.text.toString()))
                }
            }
        }
    }

    private fun attachObserve() {
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.responAddSiswa.observe(viewLifecycleOwner, Observer { showAddNote(it) })
        viewModel.responUpdateSiswa.observe(viewLifecycleOwner, Observer { showUpdateNote(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showAddNote(it: Unit?) {
        Toast.makeText(context, "Siswa berhasil disimpan", Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }

    private fun showUpdateNote(it: Unit?) {
        Toast.makeText(context, "Siswa berhasil diupdate", Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }
}