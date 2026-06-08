package com.example.trizaapps.home.pertemuan13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trizaapps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    // 1. Deklarasikan View Binding untuk Activity
    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi View Binding
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Terapkan Toolbar beserta Tombol Back (Sesuai Poin 3 di Soal)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Aksi kembali ke halaman utama saat tombol back ditekan
        }

        // 4. Pasang Adapter ke ViewPager2
        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        // 5. Hubungkan TabLayout dengan ViewPager2 (Sesuai Poin 4 di Soal)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Capture"
                1 -> "Scan"
                2 -> "QR Code"
                else -> "Tab"
            }
        }.attach()
    }
}