package com.flowers.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to the shop placeholder"
    }
    val text: LiveData<String> = _text
}