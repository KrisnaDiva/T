package com.krisna.diva.topdrakor

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krisna.diva.topdrakor.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drakor = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Drakor>(DATA, Drakor::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Drakor>(DATA)
        }

        if (drakor != null) {
            binding.tvName.text = drakor.name
            binding.ivImage.setImageResource(drakor.photo)
            binding.tvSynopsis.text = drakor.synopsis
            binding.tvYear.text = "(${drakor.year})"
        }

        binding.actionShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.setType("text/plain")
            share.putExtra(Intent.EXTRA_TEXT, binding.tvName.text)
            startActivity(Intent.createChooser(share, "Share Link"))

        }
    }

}