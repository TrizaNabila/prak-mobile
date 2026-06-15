package com.example.trizaapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.trizaapps.home.FragmentHome
import com.example.trizaapps.Note.FragmentNote
import com.example.trizaapps.home.Pertemuan4.FourthActivity
import com.example.trizaapps.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Bagian bawah (bottom) di-set 0 agar Bottom Navigation menempel pas di paling bawah layar
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // PENTING: Saat aplikasi pertama kali dibuka, langsung tampilkan FragmentHome di dalam wadah
        if (savedInstanceState == null) {
            replaceFragment(FragmentHome())
        }

        // --- INI LOGIKA BARU UNTUK MERESPON KLIK MENU BOTTOM NAV ---
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(FragmentHome())
                    true
                }
                R.id.message -> {
                    // Jika kamu punya FragmentMessage di project, tinggal buka tanda komen di bawah ini:
                    // replaceFragment(FragmentMessage())
                    true
                }
                R.id.note -> {
                    replaceFragment(FragmentNote()) // Berpindah ke fragment Note baru sesuai modul
                    true
                }
                else -> false
            }
        }

        // Tombol bawaan kamu ke Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Tombol bawaan kamu untuk Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit {
                        clear()
                    }
                    dialog.dismiss()
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    // Fungsi tambahan untuk menukar isi fragment di dalam FrameLayout secara dinamis
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}