package com.demo.compose.statetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Vikram Mane on 10/15/24.
 */
class StateTestViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _surname = MutableLiveData<String>()
    val surname : LiveData<String> = _surname

    fun nameUpdate(newName : String){
        _name.value = newName
    }

    fun surnameUpdate(newSurName : String){
        _surname.value = newSurName
    }
}