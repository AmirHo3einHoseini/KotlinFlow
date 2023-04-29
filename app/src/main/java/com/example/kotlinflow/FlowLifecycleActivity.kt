package com.example.kotlinflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.databinding.ActivityFlowLifecycleBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FlowLifecycleActivity : AppCompatActivity() {
    private val TAG = "flowLifecycleTag"
    private val flowCatch = flow {
        emit("Mohammad")
        throw Throwable("Error Message")
    }
    private lateinit var binding: ActivityFlowLifecycleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            btnGetData.setOnClickListener {
                lifecycleScope.launch {

                    flowWithOnEach()
                }
            }
        }
    }

    private suspend fun flowWithOnStart() {
        flowOf(1, 2, 3, 4, 5)
            .onStart {
                emit(0)
                binding.txtShowData.append("Flow started\n")
            }
            .collect {
                binding.txtShowData.append("$it\n")
            }

    }

    private suspend fun flowWithOnCompleted() {
        flowOf(2, 4, 6, 8, 10)
            .onCompletion {
                emit(0)
                binding.txtShowData.append("Flow completed\n")
            }
            .collect {
                binding.txtShowData.append("$it\n")
            }
    }

    private suspend fun flowWithOnEach() {
        flowOf(1, 2, 3, 4, 5)
            .onEach {
                delay(1000)
            }
            .collect {
                binding.txtShowData.append("$it\n")
            }
    }

    private suspend fun flowOnEmpty() {
        flow<List<Int>> {}
            .onEmpty {
                emit(emptyList())
                binding.txtShowData.append("it's empty\n")
            }
            .collect {
                binding.txtShowData.append("$it\n")
                Log.e(TAG, "flowWithOnEmpty")
            }
    }

    private suspend fun flowWithCatch() {
        flowCatch.catch {
            emit("Show error")
        }.collect {
            binding.txtShowData.append("$it\n")
        }
    }

    private suspend fun flowWithAll() {
        flowOf(1, 2, 3, 4, 5, 6, 7)
            .onEach {
                delay(1000)
            }.onStart {
                binding.txtShowData.append("Start\n")
            }.onCompletion {
                binding.txtShowData.append("completed\n")
            }.catch {
                binding.txtShowData.append("Error\n")
            }.onEmpty {
                binding.txtShowData.append("Empty\n")
            }
            .collect {
                binding.txtShowData.append("$it\n")

            }
    }


}