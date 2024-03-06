package mx.unam.fi.corrutinasapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel:ViewModel(){
    var resultState by mutableStateOf("")
        private set
    var counTime by mutableStateOf(0)
        private set
    var oneCount by mutableStateOf(false)
        private set
    fun fetchData(){
       val job =  viewModelScope.launch {
            for(i in 1..5){
                delay(1000)
                counTime = i
            }
           oneCount  = true
        }
        if (oneCount){
            job.cancel()
        }

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta de la API o la Web"
        }
    }
}