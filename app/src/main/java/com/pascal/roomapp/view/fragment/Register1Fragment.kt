package com.pascal.roomapp.view.fragment

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
import com.pascal.roomapp.model.User
import com.pascal.roomapp.viewModel.ViewModelUser
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModelUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelUser::class.java)
        viewModel.showUserView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btn_next.setOnClickListener(this)
        register1_back.setOnClickListener(this)

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responAddUser.observe(viewLifecycleOwner, Observer { showAddView(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showAddView(it: Unit?) {
        Toast.makeText(context, "User berhasil disimpan", Toast.LENGTH_SHORT).show()
        viewModel.showUserView()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_next -> {
                if (register1_name.text.toString().isEmpty()) {
                    register1_name.error = "Nama harus diisi"
                } else if (register1_email.toString().isEmpty()) {
                    register1_email.error = "Email harus diisi"
                } else {
                    if (register1_password.text.toString().isEmpty()) {
                        register1_password.error = "Password harus diisi"
                    } else if (register1_password2.text.toString().isEmpty()) {
                        register1_password2.error = "Confirmation password harud diisi"
                    } else {

                        if (register1_password.text.toString()
                                .equals(register1_password2.text.toString())
                        ) {

                            viewModel.addUserView(
                                User(
                                    null,
                                    register1_name.text.toString(),
                                    register1_email.text.toString(),
                                    register1_password.text.toString()
                                )
                            )

                            val item = User(
                                null, register1_name.text.toString(),
                                register1_email.text.toString(), register1_password.text.toString()
                            )

                            val bundle = bundleOf(
                                "id" to item.id,
                                "name" to item.nama,
                                "email" to item.email,
                                "pass" to item.password
                            )
                            navController.navigate(
                                R.id.action_register1Fragment_to_resultFragment, bundle
                            )

                        } else {
                            Toast.makeText(context, "password tidak cocok", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

            R.id.register1_back -> activity?.onBackPressed()
        }
    }
}