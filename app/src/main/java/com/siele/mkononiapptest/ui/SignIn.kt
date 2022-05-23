package com.siele.mkononiapptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentSignInBinding
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker

class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater)
        (activity as DrawerAndBarsLocker?)?.setDrawerLocked(true)
        (activity as DrawerAndBarsLocker?)?.setBarsLocked(true)

        binding.apply {
            buttonSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_signIn_to_customersInsights)
               /* lifecycleScope.launch {
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

                }*/
            }
            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signIn_to_signUp)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as DrawerAndBarsLocker?)?.setDrawerLocked(false)
        (activity as DrawerAndBarsLocker?)?.setBarsLocked(false)
    }

}