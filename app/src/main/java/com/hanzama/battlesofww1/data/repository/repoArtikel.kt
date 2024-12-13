package com.hanzama.battlesofww1.data.repository

import com.hanzama.battlesofww1.data.contoh
import com.hanzama.battlesofww1.data.model.dataArtikel

class repoArtikel {
    fun getArtikel(): List<dataArtikel> {
        return contoh
    }

    fun getArtikelById(id: Int): dataArtikel? {
        return contoh.find { it.id == id }
    }
}