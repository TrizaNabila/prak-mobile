package com.example.trizaapps.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trizaapps.Message.tutorial.TutorialMessageActivity
import com.example.trizaapps.R

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Meng-inflate layout bawaan fragment_message
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Mengubah judul di Toolbar/ActionBar menjadi "Message"
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Message"

        // 2. Mengaktifkan fitur option menu pada fragment ini sesuai modul
        setHasOptionsMenu(true)
    }

    // 3. Menghubungkan file menu xml (message_toolbar_menu) ke Toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // 4. Menangani aksi ketika ikon info diklik agar pindah ke halaman tutorial
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}