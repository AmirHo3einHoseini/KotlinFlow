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
    private lateinit var staticNameFlow: Flow<String>
    private lateinit var dynamicNumbersFlow: Flow<Int>
    private lateinit var collectionFlow: Flow<Int>
    private val myList = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18)

    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStaticFlow()
        setupDynamicFlow()
        setupCollectionFlow()
        intiView()


    }

    private fun intiView() {
        binding.apply {
            btn.setOnClickListener {
                lifecycleScope.launch {
//                simpleFlow()

                    // use this part for testing setupStaticFlow

//                    staticNameFlow.collect {
//                        binding.txt.append("$it\n")
//                    }

                    //use this part for testing setupDynamicFlow
//                    dynamicNumbersFlow.collect {
//                        binding.txt.append("$it\n")
//                    }

                    //use this part for testing setupCollectionFlow
//                    collectionFlow.collect {
//                        binding.txt.append("$it\n")
//                    }


                    //use this to test convertFlow
//                    convertToFLow()

                    convertFunToFlow()
                }
            }
            btnNext.setOnClickListener {
                Intent(this@MainActivity, FlowLifecycleActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }


    private suspend fun simpleFlow() {
        flowOf(1, 2, 4, 5, 7, 9)
            .collect {
                binding.txt.append("$it\n")
            }
    }

    private fun setupStaticFlow() {
        staticNameFlow = flowOf("Ali", "Alireza", "Amir", "AmirHosein", "Hasan")
    }

    /*
    emit() :set and provide the value to flow for collecting in any where you want
    actually it helps you to set value dynamically in your flow and then use it anywhere you want
    */
    private fun setupDynamicFlow() {
        dynamicNumbersFlow = flow {
            (1..5).forEach {
                emit(it)
            }
        }
    }

    /*
    create an exciting data as flow with asFlow function  of FLOW
     */
    private fun setupCollectionFlow() {
        collectionFlow = myList.asFlow()
    }


    /*
    create flow directly with small piece of code
     */
    private fun convertToFLow() {
        lifecycleScope.launch {
            (1..20).step(2).asFlow().collect {
                binding.txt.append("$it\n")
            }
        }
    }


    /*
    this two function getUserName() & convertFunToFlow()
    show you  how to get data from another function and then change and use it as
    flow with asFlow function
   */
    private suspend fun getUserName(): String {
        delay(2000)
        return " UserName : AmirHosein Hoseini"
    }

    private suspend fun convertFunToFlow() {
        ::getUserName.asFlow().collect {
            binding.txt.append("$it\n")
        }
    }
}