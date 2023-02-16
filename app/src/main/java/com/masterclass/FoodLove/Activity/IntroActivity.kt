 package com.masterclass.FoodLove.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.masterclass.FoodLove.R
 class IntroActivity : AppCompatActivity() {

 private lateinit var startBtn: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        startBtn = findViewById(R.id.startBtn)
        startBtn.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}