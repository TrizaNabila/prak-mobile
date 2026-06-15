package com.example.trizaapps.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trizaapps.databinding.FragmentHomeBinding
import com.example.trizaapps.home.pertemuan13.ThirteenthActivity // Perbaikan nama package di sini

class FragmentHome : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPertemuan3.setOnClickListener {
            val intent = Intent(requireContext(), com.example.trizaapps.home.pertemuan_3.ThirdActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan5.setOnClickListener {
            val intent = Intent(requireContext(), com.example.trizaapps.home.pertemuan_5.FifthActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}