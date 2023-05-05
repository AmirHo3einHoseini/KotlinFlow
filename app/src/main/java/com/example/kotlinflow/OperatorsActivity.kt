package com.example.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.databinding.ActivityOperatorsBinding
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

class OperatorsActivity : AppCompatActivity() {
    private val TAG = "operators log"
    private lateinit var binding: ActivityOperatorsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperatorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnOperatorData.setOnClickListener {
                lifecycleScope.launch {
//for testing each operator call one of the methods below here
                    foldFlow()
                }
            }
        }
    }


    private suspend fun takeFlow() {
        (1..10).asFlow().take(3).collect {
            binding.txtShowOperatorData.append("$it\n")
        }
    }

    private suspend fun mapFlow() {
        (1..10).asFlow().map {
            it * 2
        }.collect {
            binding.txtShowOperatorData.append("$it\n")
        }
    }

    private suspend fun filterFlow() {
        (1..10).asFlow().filter {
            it % 2 == 0
        }.collect {
            binding.txtShowOperatorData.append("$it\n")
        }
    }

    private suspend fun takeWhileFlow() {
        (1..10).asFlow().takeWhile {
            it < 5
        }.collect {
            binding.txtShowOperatorData.append("$it\n")
        }
    }

    private suspend fun repeatFlow() {
        flow {
            repeat(3) {
                (1..3).forEach {
                    emit(it)
                }
            }
        }.collect {
            binding.txtShowOperatorData.append("$it\n")
        }
    }

    private suspend fun countFlow() {
        val count = (1..10).asFlow()
            .count {
                it % 2 == 0
            }
        binding.txtShowOperatorData.append("$count\n")
    }

    private suspend fun reduceFlow() {
        val reduce = (1..5).asFlow()
            .reduce { accumulator, value ->
                accumulator + value
                Log.d(TAG, "reduceFlow: $accumulator  - $value")
            }
        binding.txtShowOperatorData.append("$reduce\n")
    }

    private suspend fun reduceStringFlow() {
        val reduce = flowOf("amir", "hosein", "amir", "ali")
            .reduce { accumulator, value ->
                Log.d(TAG, "reduceFlow: $accumulator  - $value")
                accumulator + value
            }
        binding.txtShowOperatorData.append("$reduce\n")
    }

    private suspend fun foldFlow() {
        val fold = (1..5).asFlow()
            .fold("numbers") { acc, value ->
                Log.d(TAG, "reduceFlow: $acc  - $value")
                acc + value
            }
        binding.txtShowOperatorData.append("$fold\n")
    }


}