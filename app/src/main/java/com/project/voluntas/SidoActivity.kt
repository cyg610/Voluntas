package com.project.voluntas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class SidoActivity : Activity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sido)

        //차후 배열로 재선언할 것
        val SidoBtn1 = findViewById<Button>(R.id.Sido_Item1)
        SidoBtn1.setOnClickListener(this)
        val SidoBtn2 = findViewById<Button>(R.id.Sido_Item2)
        SidoBtn2.setOnClickListener(this)
        val SidoBtn3 = findViewById<Button>(R.id.Sido_Item3)
        SidoBtn3.setOnClickListener(this)
        val SidoBtn4 = findViewById<Button>(R.id.Sido_Item4)
        SidoBtn4.setOnClickListener(this)
        val SidoBtn5 = findViewById<Button>(R.id.Sido_Item5)
        SidoBtn5.setOnClickListener(this)
        val SidoBtn6 = findViewById<Button>(R.id.Sido_Item6)
        SidoBtn6.setOnClickListener(this)
        val SidoBtn7 = findViewById<Button>(R.id.Sido_Item7)
        SidoBtn7.setOnClickListener(this)
        val SidoBtn8 = findViewById<Button>(R.id.Sido_Item8)
        SidoBtn8.setOnClickListener(this)
        val SidoBtn9 = findViewById<Button>(R.id.Sido_Item9)
        SidoBtn9.setOnClickListener(this)
        val SidoBtn10 = findViewById<Button>(R.id.Sido_Item10)
        SidoBtn10.setOnClickListener(this)
        val SidoBtn11 = findViewById<Button>(R.id.Sido_Item11)
        SidoBtn11.setOnClickListener(this)
        val SidoBtn12 = findViewById<Button>(R.id.Sido_Item12)
        SidoBtn12.setOnClickListener(this)
        val SidoBtn13 = findViewById<Button>(R.id.Sido_Item13)
        SidoBtn13.setOnClickListener(this)
        val SidoBtn14 = findViewById<Button>(R.id.Sido_Item14)
        SidoBtn14.setOnClickListener(this)
        val SidoBtn15 = findViewById<Button>(R.id.Sido_Item15)
        SidoBtn15.setOnClickListener(this)
        val SidoBtn16 = findViewById<Button>(R.id.Sido_Item16)
        SidoBtn16.setOnClickListener(this)
        val SidoBtn17 = findViewById<Button>(R.id.Sido_Item17)
        SidoBtn17.setOnClickListener(this)



    }
    override  fun onClick(v: View?){
        val SidoIntent = Intent(this, MainActivity::class.java)
      when(v?.id){
          R.id.Sido_Item1 ->{
              SidoIntent.putExtra("SidoKey","강원")
          }
          R.id.Sido_Item2 ->{
              SidoIntent.putExtra("SidoKey","경기")
          }
          R.id.Sido_Item3 ->{
              SidoIntent.putExtra("SidoKey","경남")
          }
          R.id.Sido_Item4 ->{
              SidoIntent.putExtra("SidoKey","경북")
          }
          R.id.Sido_Item5 ->{
              SidoIntent.putExtra("SidoKey","광주")
          }
          R.id.Sido_Item6 ->{
              SidoIntent.putExtra("SidoKey","대구")
          }
          R.id.Sido_Item7 ->{
              SidoIntent.putExtra("SidoKey","대전")
          }
          R.id.Sido_Item8 ->{
              SidoIntent.putExtra("SidoKey","부산")
          }
          R.id.Sido_Item9 ->{
              SidoIntent.putExtra("SidoKey","서울")
          }
          R.id.Sido_Item10 ->{
              SidoIntent.putExtra("SidoKey","울산")
          }
          R.id.Sido_Item11 ->{
              SidoIntent.putExtra("SidoKey","인천")
          }
          R.id.Sido_Item12 ->{
              SidoIntent.putExtra("SidoKey","전남")
          }
          R.id.Sido_Item13 ->{
              SidoIntent.putExtra("SidoKey","전북")
          }
          R.id.Sido_Item14 ->{
              SidoIntent.putExtra("SidoKey","제주")
          }
          R.id.Sido_Item15 ->{
              SidoIntent.putExtra("SidoKey","충남")
          }
          R.id.Sido_Item16 ->{
              SidoIntent.putExtra("SidoKey","충북")
          }
          R.id.Sido_Item17 ->{
              SidoIntent.putExtra("SidoKey","세종")
          }

      }
        startActivity(SidoIntent)
    }


}
