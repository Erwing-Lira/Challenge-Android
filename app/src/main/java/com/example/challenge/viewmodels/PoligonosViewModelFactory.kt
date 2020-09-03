package com.example.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.repository.PoligonosRepository

class PoligonosViewModelFactory(
    private val repository: PoligonosRepository
): ViewModelProvider.Factory {
    private val poligonosRepository: PoligonosRepository = repository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PoligonosViewModel(poligonosRepository) as T
    }
}