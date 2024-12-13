package com.hanzama.battlesofww1.ui
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.hanzama.battlesofww1.R
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanzama.battlesofww1.ui.adapter.artikelAdapter
import com.hanzama.battlesofww1.ui.viewmodel.MainVM

class MainActivity : AppCompatActivity() {
    private val viewModel: MainVM by viewModels()
    private lateinit var adapter: artikelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = artikelAdapter { artikel ->

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("artikel_id", artikel.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter


        viewModel.artikel.observe(this, Observer { artikel ->
            adapter.submitList(artikel)
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.xml.about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}