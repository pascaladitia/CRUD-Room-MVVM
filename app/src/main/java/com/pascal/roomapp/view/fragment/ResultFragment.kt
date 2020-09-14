package com.pascal.roomapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(), View.OnClickListener {

    private lateinit var navController : NavController
    var getName : String? = null
    var getEmail : String? = null
    var getPassword : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getName = arguments?.getString("name")
        getEmail = arguments?.getString("email")
        getPassword = arguments?.getString("password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        back.setOnClickListener(this)
        btn_backLogin.setOnClickListener(this)

        result_name.text = getName
        result_email.text = getEmail
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_backLogin -> {
                val bundle = bundleOf("name" to getName, "email" to getEmail, "password" to getPassword)
                navController.navigate(R.id.action_resultFragment_to_mainFragment, bundle)
            }

            R.id.back -> activity?.onBackPressed()
        }
    }
}