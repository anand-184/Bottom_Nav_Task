package com.anand.bottom_navigation_task

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.anand.bottom_navigation_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.bottomNav?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.f1-> navController?.navigate(R.id.fragment_login)
                R.id.f2->navController?.navigate(R.id.fragment_sign)
            }
            return@setOnItemSelectedListener true
        }
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_login -> {supportActionBar?.title = "Fragment One"
                binding?.bottomNav?.menu?.findItem(R.id.f1)?.isChecked =true
                }
                R.id.fragment_sign-> {supportActionBar?.title = "Fragment Two"
                binding?.bottomNav?.menu?.findItem(R.id.f2)?.isChecked = true}
            }
        }
    }
}