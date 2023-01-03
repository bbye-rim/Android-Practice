package com.example.dialactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {

            //버튼 클릭 확인용 ㅎㅎ
            //Log.d("test", "btn")

            openDial()
        }
    }

    fun openDial() {
        var dialIntent = Intent(Intent.ACTION_DIAL)
        val etInput = findViewById<EditText>(R.id.etPhoneNum)
        val strValue = etInput.text.toString()

        //dialIntent.setData(Uri.parse("tel:010-1234-1234"))
        //tell 아니고 tel임.....시밤바
        dialIntent.data = Uri.parse("tel:$strValue")
        startActivity(dialIntent)
    }
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {

            val etInput = findViewById<EditText>(R.id.etPhoneNum)
            val strValue = etInput.text.toString()

            openDial(strValue)
        }
    }

    fun openDial(number: String) {
        var dialIntent = Intent(Intent.ACTION_DIAL)


        dialIntent.data = Uri.parse("tel:"+number)
        //dialIntent.data = Uri.parse("tel:$number")
        startActivity(dialIntent)
    }
}