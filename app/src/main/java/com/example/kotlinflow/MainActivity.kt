package com.example.kotlinflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnBasic.setOnClickListener {
                Intent(this@MainActivity, BasicFlowActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            btnLifecycle.setOnClickListener {
                Intent(this@MainActivity, FlowLifecycleActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            btnOperator.setOnClickListener {
                Intent(this@MainActivity,OperatorsActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }


}