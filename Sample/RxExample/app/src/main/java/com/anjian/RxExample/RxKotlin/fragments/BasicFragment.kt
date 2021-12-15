package com.anjian.RxExample.RxKotlin.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.anjian.RxExample.databinding.ExampleLayoutBinding

import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class BasicFragment :Fragment() {

    private var _binding: ExampleLayoutBinding? = null
    private val binding get() = _binding!!

    lateinit var timeFormat: SimpleDateFormat
    lateinit var currentTime: Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExampleLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        timeFormat = SimpleDateFormat("y년 M월 d일 H시 m분 s초")

        timer()

        binding.ImageButton.setOnClickListener {
            downloadImage()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

    private fun timer(){
        Thread(Runnable {
            run {
                while(true){
                    currentTime = Date()
                    activity?.runOnUiThread {
                        run{
                            binding.TextView.text = timeFormat.format(currentTime)
                        }
                    }
                    Thread.sleep(1000)
                }
            }
        }).start()
    }

    private fun downloadImage(){
        Log.d("Basic", "image Download")
        val imageURL: String = "https://source.unsplash.com/random"

        Thread(Runnable{
            run{
                val url = URL(imageURL)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                var bitmap = BitmapFactory.decodeStream(inputStream)

                activity?.runOnUiThread {
                    run {
                        binding.ImageButton.setImageBitmap(bitmap)
                        Log.d("Basic", "image download complete")
                    }
                }
            }
        }).start()
    }

}