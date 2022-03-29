package com.ghedeon.vitrine.ui.screen.home

import androidx.lifecycle.ViewModel
import com.ghedeon.vitrine.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val personas get() = repository.getPersonas()
}