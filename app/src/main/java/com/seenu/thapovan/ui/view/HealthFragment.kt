package com.seenu.thapovan.ui.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seenu.thapovan.databinding.FragmentHealthBinding
import com.seenu.thapovan.ui.viewModel.HealthViewModel
import com.seenu.thapovan.utils.NetworkMoniter
import com.seenu.thapovan.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess
//Fragment class for Records
@AndroidEntryPoint
class HealthFragment() : Fragment() {

    private lateinit var binding: FragmentHealthBinding
    private lateinit var adapter: HealthAdapter
    private lateinit var viewModel: HealthViewModel
    private lateinit var connectionLiveData : NetworkMoniter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
	//init layout binding
        binding = FragmentHealthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
	//init viewmodel
        viewModel = ViewModelProvider(this)[HealthViewModel::class.java]
	//init recyclerview
        setupRecyclerView()
	//network moniter
        networkListener()
	//back click
	    backListener()

    }
	// function for init recycler adapter
    private fun setupRecyclerView() {
        adapter = HealthAdapter(context)
        binding.rvHealthRecord.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHealthRecord.adapter = adapter
    }
	//function for network monitor
	private fun networkListener(){
	//Network Monitor
        connectionLiveData = NetworkMoniter(requireContext())
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->

            binding.networkstatus = isNetworkAvailable

            isNetworkAvailable?.let {
                setupObservers()
            }
        }
}

	//function click listener
	private fun backListener(){
	//back click listener
        binding.imageButton.setOnClickListener {
		//Alert dialog for exit
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Are you want to close app?")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                exitProcess(-1)
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->

            }

            builder.show()
        }

}

	//  function to observe response from database response
    private fun setupObservers() {
        viewModel.healthRecord.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { data -> adapter.setItems(data.data.health) }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}