package com.example.myapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentMainBinding
import com.example.myapp.viewmodel.CounterViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it).get(CounterViewModel::class.java)
        }

        viewModel.counter.observe(viewLifecycleOwner, Observer {
            binding.counterText.text = it.toString()
        })

        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        binding.incrementBtn.setOnClickListener {
            viewModel.incrementCounter()
        }

        binding.decrementBtn.setOnClickListener {
            viewModel.decrementCounter()
        }

        binding.goToSecondFragmentBtn.setOnClickListener {
            val bundle = bundleOf("counter" to binding.counterText.text.toString())
            findNavController().navigate(R.id.action_mainFragment_to_counterDisplayFragment, bundle)
        }
    }

}



