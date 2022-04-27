package com.flowers.ui.plant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlantViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to the plant placeholder"
    }
    val text: LiveData<String> = _text
}