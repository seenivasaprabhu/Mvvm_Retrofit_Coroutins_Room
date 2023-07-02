package com.seenu.thapovan.ui.viewModel

import androidx.lifecycle.ViewModel
import com.seenu.thapovan.data.repository.HealthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Viewmodel using Hilt
@HiltViewModel
class HealthViewModel @Inject constructor(
    private val repository: HealthRepository
) : ViewModel() {
	// get the record fom database
    val healthRecord = repository.getHealthRecords()
}