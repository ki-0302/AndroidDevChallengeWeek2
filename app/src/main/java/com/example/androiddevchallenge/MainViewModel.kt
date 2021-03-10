package com.example.androiddevchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class MainViewModel: ViewModel() {

    private val startCountDown = 10

    private val _countDown = MutableLiveData(startCountDown)
    val countDown: LiveData<Int> = _countDown

    fun countUp() {
        _countDown.value = startCountDown
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                sleep(1000)
                if (_countDown.value!! <= 0) {
                    break
                }
                _countDown.postValue(_countDown.value?.minus(1))
            }
        }
    }
}
