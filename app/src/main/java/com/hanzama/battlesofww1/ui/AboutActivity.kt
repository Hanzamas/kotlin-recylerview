package com.hanzama.battlesofww1.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hanzama.battlesofww1.ui.viewmodel.AboutVM
import com.hanzama.battlesofww1.R

class AboutActivity : AppCompatActivity() {
    private val viewModel: AboutVM by viewModels()
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var descriptionTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        profileImageView = findViewById(R.id.profileImageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        nameTextView.text = viewModel.name
        emailTextView.text = viewModel.email
        descriptionTextView.text = viewModel.description
        profileImageView.setImageResource(R.drawable.profile_image)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}