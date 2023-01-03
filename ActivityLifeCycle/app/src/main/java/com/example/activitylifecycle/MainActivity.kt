package com.example.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val myRequestCode = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate()")

        val btn = findViewById<Button>(R.id.btnSendData)
        btn.setOnClickListener {

            val etInputData = findViewById<EditText>(R.id.etInputData)
            val strValue = etInputData.text.toString()

            val intent = Intent(applicationContext, SubActivity::class.java)
            intent.putExtra("input", strValue)

            //startActivity(intent)
            startActivityForResult(intent, myRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == myRequestCode) {

            if(resultCode == Activity.RESULT_OK) {

                val rstValue = data?.getStringExtra("return")
                val etInput = findViewById<EditText>(R.id.etInputData)
                etInput.setText(rstValue)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainActivity", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.d("MainActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.d("MainActivity", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("MainActivity", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy()")
    }
}