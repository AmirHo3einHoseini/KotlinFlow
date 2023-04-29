package com.example.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.databinding.ActivityOperatorsBinding
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class OperatorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOperatorsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperatorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnOperatorData.setOnClickListener {
                lifecycleScope.launch {

                }
            }
        }
    }


    private suspend fun takeFlow() {
        (1..10).asFlow()
            .take(3)
            .collect {

            }
    }


}