package com.flowers.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {

    val balance = 100

    private val _text = MutableLiveData<String>().apply {
        value = "Available money: $balance"
    }
    val text: LiveData<String> = _text
}