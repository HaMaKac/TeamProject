package com.flowers.ui.shop

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.flowers.R
import com.flowers.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shopViewModel =
            ViewModelProvider(this).get(ShopViewModel::class.java)

        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textShop
        shopViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        initializeButtons()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initializeButtons() {
        binding.button1.setOnClickListener {
            showPopUp()
        }
    }

    fun showPopUp() {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("flower purchased")
        dialogBuilder.setPositiveButton("Ok"
        ) { dialog, whichButton -> }
        val b = dialogBuilder.create()
        b.show()
    }
}