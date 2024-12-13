package com.hanzama.battlesofww1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanzama.battlesofww1.data.model.dataArtikel
import com.hanzama.battlesofww1.data.repository.repoArtikel

class MainVM : ViewModel() {
    private val repo = repoArtikel()
    private val _artikel = MutableLiveData<List<dataArtikel>>()
    val artikel: LiveData<List<dataArtikel>> get() = _artikel

    init {
        muatArtikel()
    }
    private fun muatArtikel() {
        _artikel.value = repo.getArtikel()
    }
}