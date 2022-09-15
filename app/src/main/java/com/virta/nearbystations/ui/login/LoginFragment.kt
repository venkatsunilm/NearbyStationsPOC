package com.virta.nearbystations.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.virta.nearbystations.R
import com.virta.nearbystations.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var bindingContext: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = LoginFragmentBinding.inflate(inflater, container, false)

        loginViewModel.spinner.observe(viewLifecycleOwner) {
            if (it) bindingContext.spinner.visibility = View.VISIBLE
            else bindingContext.spinner.visibility = View.GONE
        }

        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = "Test"
        val password = "password"

        loginViewModel.loggedUser.observe(viewLifecycleOwner) {
            if (it.success) {
                navigateToHome()
                Toast.makeText(context, "LOGGED IN SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
            } else
                if(!it.errorMessage.isNullOrEmpty()){
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
        }

        // TODO: Login screen partially implemented with mock data
        bindingContext.login.setOnClickListener {
            loginViewModel.login(username, password)
        }
    }

    private fun navigateToHome() {
        // few action animations while moving to other destinations
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        findNavController().navigate(R.id.stations_dest, null, options)
        bindingContext.spinner.visibility = View.VISIBLE
    }
}