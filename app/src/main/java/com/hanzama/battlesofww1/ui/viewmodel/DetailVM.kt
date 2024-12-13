package com.hanzama.battlesofww1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanzama.battlesofww1.data.model.dataArtikel
import com.hanzama.battlesofww1.data.repository.repoArtikel

class DetailVM : ViewModel() {
    private val repo = repoArtikel()
    private val _artikel = MutableLiveData<dataArtikel>()
    val artikel: LiveData<dataArtikel> get() = _artikel

    fun muatArtikel(id: Int) {
        _artikel.value = repo.getArtikelById(id)
    }

}