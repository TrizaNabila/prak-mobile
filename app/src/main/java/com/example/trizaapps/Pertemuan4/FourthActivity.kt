package com.example.trizaapps.Pertemuan4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trizaapps.R
import com.example.trizaapps.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.e("onCreate", "{nama_activity} dibuat pertama kali")
        setContentView(R.layout.activity_fourth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            // finish() digunakan untuk menutup Activity saat ini
            // dan otomatis kembali ke MainActivity (halaman sebelumnya)
            finish()
        }

        val nama = intent.getStringExtra("nama")
        val asal = intent.getStringExtra("asal")
        val umur = intent.getIntExtra("umur", 0)

        Log.i("Data Intent ==", "Nama: $nama , Usia: $umur, Asal: $asal")
        }



    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: {nama_activity} terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "{nama_activity} dihapus dari stack")
    }
}