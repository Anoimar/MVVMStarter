package com.thernat.starter.ui.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thernat.starter.Event
import com.thernat.starter.exceptions.BasicException
import com.thernat.starter.repository.MVVMRepository
import com.thernat.starter.vo.BasicElement
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by m.rafalski on 07/06/2019.
 */
class MasterViewModel @Inject constructor(private val repository: MVVMRepository) : ViewModel() {
    private val _basicElement: MutableLiveData<BasicElement> = MutableLiveData()

    private var basicJob : Job? = null

    private val _loadingStartedCommand = MutableLiveData<Event<Unit>>()
    val loadingStartedCommand: LiveData<Event<Unit>>
        get() = _loadingStartedCommand

    private val _loadingCompleteCommand = MutableLiveData<Event<Unit>>()
    val loadingCompleteCommand: LiveData<Event<Unit>>
        get() = _loadingCompleteCommand

    val basicElement: LiveData<BasicElement>
        get() = _basicElement

    fun start(){
        _loadingStartedCommand.value = Event(Unit)
        basicJob = CoroutineScope(Dispatchers.IO).launch {
                try {
                    val basicElement = repository.getAllBasicVOAsync()
                    withContext(Dispatchers.Main) {
                        _basicElement.value = basicElement
                        _loadingCompleteCommand.value = Event(Unit)
                    }
                } catch (e: BasicException) {
                    Timber.e(e)
                } finally {
                    //Hide loading, unlock locked options
                }
        }
    }

    override fun onCleared() {
        basicJob?.cancel()
    }
}