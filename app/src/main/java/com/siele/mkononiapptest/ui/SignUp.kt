package com.siele.mkononiapptest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.FragmentSignUpBinding
import com.siele.mkononiapptest.interfaces.DrawerAndBarsLocker


class SignUp : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater)
        (activity as DrawerAndBarsLocker).setDrawerLocked(true)
        (activity as DrawerAndBarsLocker).setBarsLocked(true)

        binding.apply {
            buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_signUp_to_signIn)
               /* if (signUpPassword.text.toString() == signUpConfirmPassword.text.toString()) {
                    val user = User(
                        signUpEmail.text.toString(),
                        signUpName.text.toString(),
                        signUpPassword.text.toString(),
                        signUpPhone.text.toString(),
                    )
                    lifecycleScope.launch {
                        val signUpResponse = AuthApi.apiClient.registerUser(user)
                        Log.d("SignUp", """
                            |Code: ${signUpResponse.code()}
                            |Response: $signUpResponse
                            |Message: ${signUpResponse.message()}""".trimMargin())
                        withContext(Dispatchers.Main) {
                            if (signUpResponse.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    signUpResponse.message(),
                                    Toast.LENGTH_LONG
                                ).show()
                                val dialogBinding = VerifyEmailDialogBinding.inflate(LayoutInflater.from(requireContext()))
                                dialogBinding.verifyEmail.setOnClickListener {
                                    Toast.makeText(
                                        requireContext(),
                                        "Email verification",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_signUp_to_signIn)
                                }
                                MaterialAlertDialogBuilder(requireContext())
                                    .setView(dialogBinding.root).show()
                            }

                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT)
                        .show()
                }*/
                tvResend.setOnClickListener {
                    Toast.makeText(requireContext(), "Wait for the code", Toast.LENGTH_SHORT).show()
                }
                tvVerifyPhone.setOnClickListener {
                    Toast.makeText(requireContext(), "verify phone", Toast.LENGTH_SHORT).show()
                }
            }
            tvSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_signUp_to_signIn)
            }
        }

        return binding.root
    }

}