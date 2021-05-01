package com.project.voluntas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, SidoActivity::class.java)
            startActivity(intent)
        },3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
