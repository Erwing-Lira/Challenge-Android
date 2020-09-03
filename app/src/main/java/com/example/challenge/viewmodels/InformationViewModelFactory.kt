package com.example.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge.repository.InformationRepository

class InformationViewModelFactory(
    private val repository: InformationRepository
): ViewModelProvider.Factory{
    private val informationRepository: InformationRepository = repository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InformationViewModel(informationRepository) as T
    }
}