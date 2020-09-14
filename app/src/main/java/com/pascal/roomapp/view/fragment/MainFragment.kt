package com.pascal.roomapp.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.roomapp.R
import com.pascal.roomapp.model.User
import com.pascal.roomapp.view.HomeActivity
import com.pascal.roomapp.view.fragmentNavigation.BerandaFragment
import com.pascal.roomapp.viewModel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModelUser
    private lateinit var sharePref: SharedPreferences
    private var user: User? = null
    var getName: String? = null

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getName = arguments?.getString("name")

        viewModel = ViewModelProviders.of(this).get(ViewModelUser::class.java)

        sharePref = requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.showUserView()

        attachBtn()

        viewModel.responShowUser.observe(viewLifecycleOwner, Observer { showData(it) })
        viewModel.responEmail.observe(viewLifecycleOwner, Observer { showLogin(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showData(it: List<User>?) {
        if (sharePref.getInt(LOGIN_SESSION, 0) == 1) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(LOGIN_SESSION, it?.get(0)?.nama)
            startActivity(intent)
            activity?.finish()
        } else {
            Toast.makeText(context, "Login Here", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, "email atau password salah", Toast.LENGTH_SHORT).show()
    }

    private fun showLogin(it: User) {
        Toast.makeText(context, "Selamat Datang", Toast.LENGTH_SHORT).show()

        val email = main_email.text.toString()
        val password = main_password.text.toString()

        if (password == it.password) {
            sharePref.edit().putInt(LOGIN_SESSION, 1).commit()
            Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()

            val bundle = bundleOf(LOGIN_SESSION to it.nama)
            navController.navigate(R.id.action_mainFragment_to_homeActivity, bundle)

            requireActivity().finish()

        } else {
            Toast.makeText(context, "email & password salah", Toast.LENGTH_SHORT).show()
        }


    }

    private fun attachBtn() {
        btn_login.setOnClickListener {
            val email = main_email.text.toString()
            val password = main_password.text.toString()

            loginValidation(email, password)
        }

        btn_register.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_register1Fragment)
        }
    }

    private fun loginValidation(email: String, password: String) {

        Log.d("TAG", "lloginValidation $email dan $password")

        if (email.isNotEmpty()) {
            Log.d("TAG", "lloginValidation2 $email dan $password")

            viewModel.getDataEmail(email).observe(viewLifecycleOwner, Observer { user ->
                Log.d("TAG", "lloginValidation3 $email dan $password")
                if (password == user.password) {
                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()

                    val bundle = bundleOf("name" to user.nama)
                    navController.navigate(R.id.action_mainFragment_to_homeActivity, bundle)
                } else {
                    Toast.makeText(context, "email & password salah", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(context, "email & password tidak boleh kosong", Toast.LENGTH_SHORT)
                .show()
        }
    }
}