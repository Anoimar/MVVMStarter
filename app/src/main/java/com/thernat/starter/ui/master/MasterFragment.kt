package com.thernat.starter.ui.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thernat.starter.Event
import com.thernat.starter.R
import com.thernat.starter.di.Injectable
import javax.inject.Inject
import com.thernat.starter.databinding.FragmentMasterBinding

/**
 * Created by m.rafalski on 07/06/2019.
 */
class MasterFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var masterViewModel: MasterViewModel


    private lateinit var binding : FragmentMasterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masterViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MasterViewModel::class.java).also { binding.viewmodel = it }
        initMasterFragment()
    }

    private fun initMasterFragment() {
        masterViewModel.apply {
            setEmptyEventObserver(loadingStartedCommand){ binding.isLoading = true}
            setEmptyEventObserver(loadingCompleteCommand){ binding.isLoading = false}
        }
        masterViewModel.start()
    }

    fun navController() = findNavController()


    //Helper function used to observe events from view model
    private fun setEmptyEventObserver(liveData: LiveData<Event<Unit>>, action: () -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { action()}
        })
    }

}