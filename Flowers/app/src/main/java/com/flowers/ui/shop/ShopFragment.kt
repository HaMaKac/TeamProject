package com.flowers.ui.shop

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
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
import com.flowers.MainActivity
import com.flowers.R
import com.flowers.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var balance = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root
        balance = (activity as MainActivity).globalBalance
        refreshBalanceLabel()
        initializeButtons()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initializeButtons() {

        binding.button1.setOnClickListener {
            purchaseFlower(1)
        }
        binding.button2.setOnClickListener {
            purchaseFlower(2)
        }
        binding.button3.setOnClickListener {
            purchaseFlower(3)
        }
        binding.button4.setOnClickListener {
            purchaseFlower(4)
        }
        binding.button5.setOnClickListener {
            purchaseFlower(5)
        }
        binding.button6.setOnClickListener {
            purchaseFlower(6)
        }
        binding.button7.setOnClickListener {
            purchaseFlower(7)
        }
        binding.button8.setOnClickListener {
            purchaseFlower(8)
        }
        binding.button9.setOnClickListener {
            purchaseFlower(9)
        }

    }

    fun showPopUp(num : Int) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage((activity as MainActivity).availableFlowers[num].name + " purchased for $"+num*5)
        dialogBuilder.setPositiveButton("Ok :)"
        ) { dialog, whichButton -> }
        val b = dialogBuilder.create()
        b.show()
    }

    fun showErrorPopUp(num : Int) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("$$balance is not enough to buy " +(activity as MainActivity).availableFlowers[num].name+ " ($"+num*5+ ")")
        dialogBuilder.setPositiveButton("Ok :("
        ) { dialog, whichButton -> }
        val b = dialogBuilder.create()
        b.show()
    }

    fun showAlreadyOwnedPopUp(num : Int) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setMessage("you already have " +(activity as MainActivity).availableFlowers[num].name+ "!")
        dialogBuilder.setPositiveButton("Ok"
        ) { dialog, whichButton -> }
        val b = dialogBuilder.create()
        b.show()
    }

    fun purchaseFlower(number : Int) {

        if((activity as MainActivity).availableFlowers[number].isAvailable) {
            showAlreadyOwnedPopUp(number)
        } else if(balance >= number*5) {
            (activity as MainActivity).availableFlowers[number].isAvailable = true
            updateBalance(number*5)
            showPopUp(number)
        } else {
            showErrorPopUp(number)
        }
    }

    fun updateBalance(price : Int) { //provide price (amount to be subtracted from balance)
        balance -= price
        (activity as MainActivity).globalBalance -= price
        refreshBalanceLabel()
    }

    fun refreshBalanceLabel() {
        val textView: TextView = binding.textShop
        "Available money: $balance".also { textView.text = it }
    }
}