package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registration.databinding.ActivityMain3Binding
import com.example.registration.utils.MySharedPreference

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)
        binding.btnFinish.setOnClickListener {
            MySharedPreference.switch=binding.colorSwitch.isChecked
            finish()
        }
    }
}