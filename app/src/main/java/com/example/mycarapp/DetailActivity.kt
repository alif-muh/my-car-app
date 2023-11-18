package com.example.mycarapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var shareButton: Button


    companion object{
        const val KEY_CAR = "key_car"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        shareButton = findViewById(R.id.btn_share)
        shareButton.setOnClickListener(this)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val tvDetailSpecification: TextView = findViewById(R.id.tv_detail_specification)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)



        val dataCar = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Car>(KEY_CAR, Car::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Car>(KEY_CAR)
        }

        dataCar?.let { ivDetailPhoto.setImageResource(it.photo) }
        tvDetailName.text = dataCar?.name
        tvDetailDescription.text = dataCar?.description
        tvDetailSpecification.text = dataCar?.specification

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intentToAbout = Intent(this@DetailActivity, AboutActivity::class.java)
                startActivity(intentToAbout)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_share -> {
                // Create Intent for sharing through email
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.type = "message/rfc822" // Tipe pesan email
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("tujuan@email.com")) // Alamat email penerima
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subjek Email") // Subjek email (opsional)
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Isi pesan email") // Isi pesan email (opsional)

                // Start sharing through email
                startActivity(Intent.createChooser(emailIntent, "Pilih Aplikasi Email"))
            }
        }
    }
}