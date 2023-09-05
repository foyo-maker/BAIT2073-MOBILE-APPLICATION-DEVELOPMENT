package com.example.bait2073mobileapplicationdevelopment.screens.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.bait2073mobileapplicationdevelopment.databinding.FragmentVideoTabBinding


class WorkoutVideoFragment : Fragment() {
    private lateinit var binding:FragmentVideoTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBindingUtil
        binding = FragmentVideoTabBinding.inflate(layoutInflater)

        val webView: WebView = binding.webView

        val video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QifjltKUMCk\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        return binding.root
    }
}
