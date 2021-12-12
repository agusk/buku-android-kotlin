package id.ilmudata.mynotificationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import id.ilmudata.mynotificationapp.databinding.ActivityAfterNotificationBinding
class AfterNotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}