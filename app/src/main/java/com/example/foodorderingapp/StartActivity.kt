package com.example.foodorderingapp

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.ImageView

import com.example.foodorderingapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {


    private val binding: ActivityStartBinding by lazy {
        ActivityStartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val galleryImageView = findViewById<ImageView>(R.id.gallery)
        galleryImageView.setOnClickListener {
            openGooglePhotos()
        }
        binding.nextButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


            val googleMapsImage = findViewById<ImageView>(R.id.maps)
            googleMapsImage.setOnClickListener {
                val geoUri = "geo:0,0?q=Google+Maps"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                intent.setPackage("com.google.android.apps.maps")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    // Handle if Google Maps app is not installed
                    // You can open the Google Maps website in a web browser
                    val mapsUrl = "https://maps.google.com"
                    val mapsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl))
                    startActivity(mapsIntent)
                }
            }



            val DailPhone = findViewById<ImageView>(R.id.phone)
            DailPhone.setOnClickListener {
                val phoneNumber = "" // Replace with the desired phone number
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }
            val google = findViewById<ImageView>(R.id.google)
            google.setOnClickListener {
                val searchTerm = "Your search term" // Replace with the desired search term
                val intent = Intent(Intent.ACTION_WEB_SEARCH)
                intent.putExtra(SearchManager.QUERY, searchTerm)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    // Handle if no app can handle web searches
                    // For example, you can open the Google search website in a web browser
                    val googleSearchUrl = "https://www.google.com"
                    val googleSearchIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleSearchUrl))
                    startActivity(googleSearchIntent)
                }


            }

            val email = findViewById<ImageView>(R.id.email)
            email.setOnClickListener {
                val recipientEmail =
                    "shaikhflora65@example.com" // Replace with the recipient's email address
                val subject = "Subject of your email" // Replace with the subject of your email
                val message = "Body of your email" // Replace with the body of your email

                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                intent.putExtra(Intent.EXTRA_TEXT, message)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(intent, "Send Email"))
                } else {
                    // Handle if no app can handle sending emails
                    // For example, you can show a toast message or open the email client in a web browser
                }
            }

        }


    }

    private fun openGooglePhotos() {
        val googlePhotosPackage = "com.google.android.apps.photos"
        val googlePhotosIntent = packageManager.getLaunchIntentForPackage(googlePhotosPackage)

        if (googlePhotosIntent != null) {
            startActivity(googlePhotosIntent)
        } else {
            // Google Photos app is not installed, open Google Photos website
            val googlePhotosUrl = "https://photos.google.com/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googlePhotosUrl))
            startActivity(browserIntent)
        }
    }


}

