package com.example.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        Log.d("SubActivity", "onCreate()")

        val btn = findViewById<Button>(R.id.btnResponse)
        btn.setOnClickListener {

            val etText = findViewById<EditText>(R.id.etOutputData)
            val rtnValue = etText.text.toString()

            //MainActivity로 응답 보내기
            val intent = Intent()
            intent.putExtra("return", rtnValue)
            setResult(Activity.RESULT_OK, intent)  //응답값 전달
            finish()  //두번째 Activity 종료
                      //이걸 추가해야 두번째 Activity가 사라지면서 첫번째 Activity에 값이 넘어간 것을 확인 가능
        }

        //MainActivity로부터 값 받아서 출력
        val rcvIntent = intent
        if(rcvIntent != null) {
            val etResult = findViewById<EditText>(R.id.etOutputData)
            val strValue: String? = rcvIntent.getStringExtra("input")

            etResult.setText(strValue)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("SubActivity", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.d("SubActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.d("SubActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.d("SubActivity", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("SubActivity", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("SubActivity", "onDestroy()")
    }
}