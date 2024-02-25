package com.krisna.diva.topdrakor

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.krisna.diva.topdrakor.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            binding.tvTitle.text = "${getString(R.string.title)}: ${drakor.name}"
            binding.tvGenres.text = "${getString(R.string.genres)}: ${drakor.genres}"
            binding.tvEpisodes.text = "${getString(R.string.episodes)}: ${drakor.episodes}"
            binding.tvAired.text = "${getString(R.string.aired)}: ${drakor.aired}"
            binding.tvDuration.text = "${getString(R.string.duration)}: ${drakor.duration}"
        }

        binding.actionShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.setType("text/plain")
            share.putExtra(Intent.EXTRA_TEXT, binding.tvName.text)
            startActivity(Intent.createChooser(share, "Share Link"))

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}