package com.krisna.diva.topdrakor

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krisna.diva.topdrakor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var rvDrakors: RecyclerView
    private val list = ArrayList<Drakor>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        rvDrakors = findViewById(R.id.rv_drakors)
        binding.rvDrakors.setHasFixedSize(true)
        list.addAll(getListDrakors())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListDrakors(): ArrayList<Drakor> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataGenres = resources.getStringArray(R.array.data_genres)
        val dataEpisodes = resources.getStringArray(R.array.data_episode)
        val dataAired = resources.getStringArray(R.array.data_aired)
        val dataDuration = resources.getStringArray(R.array.data_duration)
        val listDrakor = ArrayList<Drakor>()
        for (i in dataName.indices) {
            val drakor = Drakor(
                dataPhoto.getResourceId(i, -1),
                dataName[i],
                dataDescription[i],
                dataSynopsis[i],
                dataYear[i],
                dataGenres[i],
                dataEpisodes[i],
                dataAired[i],
                dataDuration[i],
            )
            listDrakor.add(drakor)
        }
        return listDrakor
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDrakors.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDrakors.layoutManager = LinearLayoutManager(this)
        }
        val listDrakorAdapter = ListDrakorAdapter(list)
        binding.rvDrakors.adapter = listDrakorAdapter

        // membuka detail
        listDrakorAdapter.setOnItemClickCallback { drakor ->
            val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
            moveWithObjectIntent.putExtra(DetailActivity.DATA, drakor)
            startActivity(moveWithObjectIntent)
        }
    }
}