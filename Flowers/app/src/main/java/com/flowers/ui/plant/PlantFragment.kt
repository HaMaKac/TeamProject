package com.flowers.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flowers.databinding.FragmentPlantBinding

class PlantFragment : Fragment() {

    private var _binding: FragmentPlantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(PlantViewModel::class.java)

        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlant
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = "Welcome to the plant placeholder"
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}