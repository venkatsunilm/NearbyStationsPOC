package com.virta.nearbystations.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.virta.nearbystations.R
import com.virta.nearbystations.databinding.WelcomeFragmentBinding
import com.virta.nearbystations.viewmodel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private lateinit var bindingContext: WelcomeFragmentBinding
    private val welcomeViewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = WelcomeFragmentBinding.inflate(inflater, container, false)
        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        bindingContext.secureSignInButton.setOnClickListener {
            if (welcomeViewModel.isUserAuthenticated) {
                findNavController().navigate(R.id.stations_dest, null, options)
            } else
                findNavController().navigate(R.id.login_dest, null, options)
            // TODO: The alternative way to call the destination using action testing, under implementation
            // Navigation.createNavigateOnClickListener(R.id.action_welcome_dest_to_home_dest, null)
        }
    }
}