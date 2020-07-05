package br.edu.ifsp.scl.urbandictsdm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.urbandictsdm.model.Available
import br.edu.ifsp.scl.urbandictsdm.model.Conversion
import br.edu.ifsp.scl.urbandictsdm.model.MoneyService

class UrbanDictionaryViewModel(context: Context): ViewModel() {
    private val model = MoneyService(context)

    fun getAvailable(): MutableLiveData<Available> {
        return model.getAvailable()
    }

    fun converter(currencies1: String, currencies2: String, value: String): MutableLiveData<Conversion> {
        return model.converter(currencies1, currencies2, value)
    }
}