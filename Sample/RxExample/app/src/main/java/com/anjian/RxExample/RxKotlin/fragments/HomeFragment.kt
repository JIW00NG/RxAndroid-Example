package com.anjian.RxExample.RxKotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.anjian.RxExample.databinding.MainLayoutBinding

class HomeFragment : Fragment() {

    private var _binding: MainLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MainLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.MoveBasicButton.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToBasicFragment()
            view.findNavController().navigate(action)
        }
        binding.MoveRxButton.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToRxFragment()
            view.findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}