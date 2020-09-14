package com.pascal.roomapp.view.fragmentNavigation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pascal.roomapp.viewModel.ViewModelUser
import com.pascal.roomapp.R
import kotlinx.android.synthetic.main.fragment_beranda.*

class BerandaFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModelUser

    companion object {
        const val NAME = "LOGIN"
        const val LOGIN_SESSION = "login_session"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelUser::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        beranda_logout.setOnClickListener(this)

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseDeleteUser.observe(viewLifecycleOwner, Observer { showDeleteUser(it) })
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })

    }

    private fun showDeleteUser(it: Unit) {
        Toast.makeText(context, "Anda berhasil logout", Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.beranda_logout -> {
                AlertDialog.Builder(context).apply {
                    setTitle("Logout")
                    setMessage("Anda yakin ingin logout?")
                    setCancelable(false)

                    setPositiveButton("Yes") { dialogInterface, i ->
                        viewModel.deleteUserView()
                        requireActivity().getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
                            .putInt(LOGIN_SESSION, 0).commit()
                        navController.navigate(R.id.action_berandaFragment_to_mainActivity)
                        activity?.finish()
                    }

                    setNegativeButton("Cancel") { dialogInterface, i ->
                        dialogInterface?.dismiss()
                    }
                }.show()
            }
        }
    }


}