package com.akarmoon.mosquemarker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.akarmoon.mosquemarker.BaseApplication
import com.akarmoon.mosquemarker.R
import com.akarmoon.mosquemarker.databinding.FragmentMarkerListBinding
import com.akarmoon.mosquemarker.ui.adapter.MarkerListAdapter
import com.akarmoon.mosquemarker.ui.viewmodel.MarkerViewModel
import com.akarmoon.mosquemarker.ui.viewmodel.MarkerViewModelFactory

class MarkerListFragment : Fragment() {

    private val viewModel: MarkerViewModel by activityViewModels {
        MarkerViewModelFactory((activity?.application as BaseApplication).database.markerDao())
    }

    private var _binding: FragmentMarkerListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MarkerListAdapter { marker ->
            findNavController().navigate(
                MarkerListFragmentDirections.actionMarkerListFragmentToMarkerDetailFragment(
                    marker.id
                )
            )
        }
        viewModel.allMarkers.observe(this.viewLifecycleOwner) { marker ->
            marker.let { adapter.submitList(it) }
        }
        binding.apply {
            recyclerView.adapter = adapter
            addMarkerFab.setOnClickListener {
                findNavController().navigate(R.id.action_markerListFragment_to_addMarkerFragment)
            }
        }
    }
}