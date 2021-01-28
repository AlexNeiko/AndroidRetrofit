package com.alexneiko.androidretrofitrestapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val firstName : String = intent.getStringExtra("firstname").toString()
        val lastName : String = intent.getStringExtra("lastname").toString()
        val email : String = intent.getStringExtra("email").toString()
        val image : String = intent.getStringExtra("image").toString()

        val nameStr : String = "$firstName $lastName"

        detailActivityNameTextView.text = nameStr
        detailActivityEmailTextView.text = email

        Picasso.with(this).load(image).into(detailActivityAvatarImageView)

    }
}