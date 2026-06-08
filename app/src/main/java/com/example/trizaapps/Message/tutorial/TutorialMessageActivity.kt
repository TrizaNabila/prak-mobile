package com.example.trizaapps.Message.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.trizaapps.MainActivity
import com.example.trizaapps.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Daftar fragment tutorial yang akan tampil bergantian
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // Hubungkan data ke adapter komponen ViewPager2
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter

        // Konfigurasi internal ViewPager2 agar geserannya halus tanpa overscroll bawaan yang kaku
        with(binding.tutorialMessageViewPager) {
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        // 3. Sesuai poin 3 di modul: Hubungkan Dotsindicator dengan TutorialMessageViewPager
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)

        // --- TAMBAHAN LOGIKA BARU UNTUK TOMBOL DI SINI ---

        // Memantau pergeseran halaman pada ViewPager2
        binding.tutorialMessageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Karena ada 3 fragment, indeksnya dimulai dari 0:
                // 0 = Tutorial1Fragment, 1 = Tutorial2Fragment, 2 = Tutorial3Fragment (Slide Terakhir)
                if (position == 2) {
                    binding.btnToDashboard.visibility = View.VISIBLE // Munculkan tombol
                } else {
                    binding.btnToDashboard.visibility = View.GONE // Sembunyikan tombol
                }
            }
        })

        // Aksi ketika tombol "Mulai Gunakan Sekarang" diklik
        binding.btnToDashboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup halaman tutorial agar kalau user menekan tombol 'Back' tidak balik lagi ke sini
        }
    }
}