package com.erdemyesilcicek.taletap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erdemyesilcicek.taletap.databinding.FragmentMainBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.creatorButton.setOnClickListener { creatorButtonClicked(it) }

    }

    public fun creatorButtonClicked(view: View){
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = "AIzaSyB9EovNJ98Y86MbZOPyE8qdKfDOFRZphfE"
        )

        val prompt = "Kısa ve anlamlı bir masal yaz, maksimum 2 paragraf olsun. Masalda dilediğin karakterleri kullanabilirsin. Masalın sonunda hayatın anlamı ile ilgili bir ders verilsin veya korkunç bir son ile bitsin."
        MainScope().launch {
            val response = generativeModel.generateContent(prompt)
            //println(response.text)
            binding.textView.text = response.text
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}