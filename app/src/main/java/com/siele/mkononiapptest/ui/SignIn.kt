package com.siele.mkononiapptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.api.AuthApi
import com.siele.mkononiapptest.databinding.FragmentSignInBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  val drawer = requireActivity().findViewById<DrawerLayout>(R.id.sideNav)
          drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater)

        binding.apply {
            buttonSignIn.setOnClickListener {
                lifecycleScope.launch {
                    val loginResponse = AuthApi.apiClient.loginUser(
                        signInPhone.toString(),
                        signInPassword.text.toString()
                    )

                    withContext(Dispatchers.Main) {
                        if (loginResponse.isSuccessful) {
                            findNavController().navigate(R.id.action_signIn_to_customersInsights)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                loginResponse.message(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }
            }
            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signIn_to_signUp)
            }
        }


        return binding.root
    }

}