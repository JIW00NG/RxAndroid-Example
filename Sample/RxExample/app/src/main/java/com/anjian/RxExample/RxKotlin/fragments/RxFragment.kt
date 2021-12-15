package com.anjian.RxExample.RxKotlin.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import com.anjian.RxExample.databinding.ExampleLayoutBinding

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RxFragment : Fragment() {
    private var _binding: ExampleLayoutBinding? = null
    private val binding get() = _binding!!

    lateinit var timeFormat: SimpleDateFormat
    lateinit var currentTime: Date

    lateinit var disposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExampleLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        disposable = CompositeDisposable()

        timeFormat = SimpleDateFormat("y년 M월 d일 H시 m분 s초")

        rxTimer()

        binding.ImageButton.setOnClickListener {
            rxDownloadImage()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
        disposable.clear()
    }

    private fun rxTimer(){
        val timerObservable = Observable.interval(0, 1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        timerObservable.subscribe({
            currentTime = Date()
            binding.TextView.text = timeFormat.format(currentTime)
        }, {e ->
            Log.e("Rx", "timer error")
        },{
        }, disposable)
    }

    private fun rxDownloadImage(){
        Log.d("Rx", "image download")
        val imageURL = "https://source.unsplash.com/random"

        val imageDownloadObservable = Observable.just(imageURL)
            .subscribeOn(Schedulers.io())
            .map{url: String -> URL(url)}
            .map { url: URL -> url.openConnection() as HttpURLConnection }
            .map { connection: HttpURLConnection ->
                connection.doInput = true
                connection.connect()
                connection
            }
            .map { obj: HttpURLConnection -> obj.inputStream }
            .map { inputStream: InputStream? -> BitmapFactory.decodeStream(inputStream)}
            .observeOn(AndroidSchedulers.mainThread())

        imageDownloadObservable.subscribe({
            binding.ImageButton.setImageBitmap(it)
        },{
            Log.e("Rx", "image download error")
        },{
            Log.d("Rx", "image downloaded")
        },disposable)
    }
}