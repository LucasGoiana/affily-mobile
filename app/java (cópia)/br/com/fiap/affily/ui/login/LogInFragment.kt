package br.com.fiap.affily.ui.login


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.affily.R

import br.com.fiap.affily.databinding.FragmentLogInBinding


class LogInFragment : Fragment(R.layout.fragment_log_in) {

    private lateinit var binding: FragmentLogInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLogInBinding.bind(view)
        binding.btLogin.setOnClickListener{
//            val action = LogInFragmentDirections.actionLogInFragmentToItemFragment()
//            findNavController().navigate(action)
        }

        binding.tvResetPassword.setOnClickListener{
//            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
//            findNavController().navigate(action)
        }
            //

    }
}