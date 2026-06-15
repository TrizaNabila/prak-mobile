package com.example.trizaapps.home.pertemuan13

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.trizaapps.databinding.FragmentTabCaptureBinding
import com.example.trizaapps.utils.PermissionHelper

class TabCaptureFragment : Fragment() {

    // Mengaktifkan View Binding untuk Fragment
    private var _binding: FragmentTabCaptureBinding? = null
    private val binding get() = _binding!!

    private var currentPhotoUri: Uri? = null

    // 3️⃣ POIN 3: Definisi variable cameraLauncher untuk menangani hasil kamera
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoUri?.let { uri ->
                binding.ivCapturedImage.setImageURI(uri)
                // Refresh galeri
                requireContext().sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            }
        }
    }

    // 3️⃣ POIN 3: Definisi variable permissionLauncher untuk konfirmasi izin kamera
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(requireContext(), "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menggunakan View Binding untuk inflate layout
        _binding = FragmentTabCaptureBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 4️⃣ POIN 4: Tambahkan function onViewCreated() untuk menangani onClick pada btnCapture
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCapture.setOnClickListener {
            if (!PermissionHelper.hasPermission(
                    requireActivity(),
                    Manifest.permission.CAMERA)) {
                PermissionHelper.requestPermission(
                    permissionLauncher,
                    Manifest.permission.CAMERA
                )
            } else {
                openCamera()
            }
        }
    }

    // 5️⃣ POIN 5: Lengkapi dengan fungsi pendukung untuk membuka kamera dan menyimpan gambar
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Note: For Android 11+ (API 30+), ensure queries are added to Manifest if resolveActivity is used
        // Since it's a standard intent, we proceed with launching.
        currentPhotoUri = createGalleryPhotoUri()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        cameraLauncher.launch(intent)
    }

    private fun createGalleryPhotoUri(): Uri {
        val folderName = "TestCaptures"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/${folderName}")
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw RuntimeException("Gagal membuat URI MediaStore")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding agar tidak terjadi kebocoran memori (memory leak)
        _binding = null
    }
}