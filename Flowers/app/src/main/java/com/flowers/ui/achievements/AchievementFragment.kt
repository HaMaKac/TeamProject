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
            Toast.makeText(activity, "You have planted 10 flowers!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton18.setOnClickListener{
            Toast.makeText(activity, "You have planted 50 flowers!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton23.setOnClickListener{
            Toast.makeText(activity, "You have planted 100 flowers!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton21.setOnClickListener{
            Toast.makeText(activity, "You have focused for 3 hours!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton17.setOnClickListener{
            Toast.makeText(activity, "You have focused for 6 hours!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton24.setOnClickListener{
            Toast.makeText(activity, "You have focused for 9 hours!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton19.setOnClickListener{
            Toast.makeText(activity, "You have bought 3 kinds of flowers!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton16.setOnClickListener{
            Toast.makeText(activity, "You have bought 6 kinds of flowers!", Toast.LENGTH_LONG).show()
        }
        binding.imageButton22.setOnClickListener{
            Toast.makeText(activity, "You have bought 9 kinds of flowers!", Toast.LENGTH_LONG).show()
        }
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}