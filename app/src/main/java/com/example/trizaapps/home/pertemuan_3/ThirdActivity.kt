package com.example.trizaapps.home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trizaapps.R
import com.example.trizaapps.databinding.ActivityThirdBinding
import com.example.trizaapps.utils.NotificationHelper
import com.example.trizaapps.utils.PermissionHelper
import com.example.trizaapps.utils.ReminderHelper
import java.util.Calendar

import android.view.MenuItem

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Third"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // Cek izin notifikasi untuk Android 13+ saat halaman diakses
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnKirim.setOnClickListener {
            if (PermissionHelper.isNotificationPermissionRequired()) {
                val permission = Manifest.permission.POST_NOTIFICATIONS
                if (!PermissionHelper.hasPermission(this, permission)) {
                    PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
                } else {
                    sendLocalNotification()
                }
            } else {
                sendLocalNotification()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendLocalNotification() {
        val noTujuan = binding.inputNoTujuan.text.toString()
        val intent = Intent(this, ThirdResultActivity::class.java)

        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
        }

        ReminderHelper.setReminder(
            context = this,
            hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            title = "Reminder 1 Menit",
            message = "Halo $noTujuan, reminder ini muncul 1 menit setelah tombol ditekan",
            targetActivity = ThirdResultActivity::class.java
        )

        Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()
    }
}
