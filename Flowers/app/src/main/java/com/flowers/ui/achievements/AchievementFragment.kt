package com.flowers.ui.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flowers.databinding.AchievementFragmentBinding

class AchievementFragment : Fragment() {

    private var _binding: AchievementFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AchievementFragmentBinding.inflate(inflater, container, false)
        binding.imageButton20.setOnClickListener{
            Toast.makeText(activity, "123", Toast.LENGTH_LONG).show()
        }
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}