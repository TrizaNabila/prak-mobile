package com.example.trizaapps.Message.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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
    }
}