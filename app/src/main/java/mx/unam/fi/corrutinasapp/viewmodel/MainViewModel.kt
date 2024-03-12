package mx.unam.fi.corrutinasapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel:ViewModel(){
    var resultState by mutableStateOf("")
        private set
    var countTime by mutableStateOf(0)
        private set
    var countTime2 by mutableStateOf(0)
        private set
    var oneCount by mutableStateOf(false)
        private set
    fun fetchData() {
        viewModelScope.launch {
            for (i in 1..5) {
                delay(1000)
                countTime = i
            }
            oneCount = true
        }

        viewModelScope.launch {
            while (!oneCount) {

                delay(100)
            }
            for (i in 1..10) {
                delay(1000)
                countTime2 = i
            }
            resultState = "Respuesta de la API o la Web"
            countTime = 0
            countTime2 = 0
            oneCount = false

        }

    }

    fun cancelCounters() {
        // Cancelar todos los trabajos en viewModelScope
        viewModelScope.coroutineContext.cancelChildren()
        // Reiniciar los contadores
        countTime = 0
        countTime2 = 0
        oneCount = false
    }

}