package golab.dev.vitalfix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import golab.dev.androidvitalfix.ActivityThreadHooker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}