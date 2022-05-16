package com.flowers.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flowers.R
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
        val plantViewModel =
            ViewModelProvider(this).get(PlantViewModel::class.java)

        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlant
        plantViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        //////////////
        ///Placeholder flower, since buying them is not implemented yet
        class PlaceholderFlower(name: String, image : Int){
            val name = name
            val image = image
        }
        val flowers = mutableListOf(PlaceholderFlower("SomeFlower1", R.drawable.ic_baseline_gamepad_24),PlaceholderFlower("SomeFlower2", R.drawable.flower_example))
        val flowersNames = mutableListOf<String>()
        flowers.iterator().forEach {
            flowers -> flowersNames.add(flowers.name)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, flowersNames)

        binding.boughtFlowers.adapter = arrayAdapter

        binding.boughtFlowers.setOnItemClickListener{adapterView, view, i, l ->
        Toast.makeText(requireContext(), flowers[i].name, Toast.LENGTH_LONG).show()
        binding.plantedPlant.setImageResource(flowers[i].image)}
        //////////////

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}