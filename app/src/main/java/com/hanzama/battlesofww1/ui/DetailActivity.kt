package com.hanzama.battlesofww1.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hanzama.battlesofww1.ui.viewmodel.DetailVM
import com.hanzama.battlesofww1.R
import java.io.File
import java.io.FileOutputStream

class DetailActivity : AppCompatActivity()  {

    private val viewModel: DetailVM by viewModels()
    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        titleTextView = findViewById(R.id.titleTextView)
        contentTextView = findViewById(R.id.contentTextView)
        imageView = findViewById(R.id.imageView)
        shareButton = findViewById(R.id.shareButton)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val artikelid = intent.getIntExtra("artikel_id", -1)


        viewModel.muatArtikel(artikelid)


        viewModel.artikel.observe(this, Observer { artikel ->
            artikel?.let {
                titleTextView.text = it.title
                contentTextView.text = it.content
                Glide.with(this).load(it.imageUrl).into(imageView)
            }
        })


        shareButton.setOnClickListener {
            val artikel = viewModel.artikel.value
            artikel?.let {
                val bitmap = (imageView.drawable as BitmapDrawable).bitmap
                val file = File(externalCacheDir, "shared_image.png")
                val fOut = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
                fOut.flush()
                fOut.close()
                file.setReadable(true, false)
                val uri = FileProvider.getUriForFile(this, "$packageName.fileprovider", file)

                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    putExtra(Intent.EXTRA_TEXT, "Coba lihat Artikel Ini: ${it.title}\n\n${it.content}")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}