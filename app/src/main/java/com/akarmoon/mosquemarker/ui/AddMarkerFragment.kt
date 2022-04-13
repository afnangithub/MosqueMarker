package com.akarmoon.mosquemarker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akarmoon.mosquemarker.R
import com.akarmoon.mosquemarker.databinding.FragmentAddMarkerBinding
import com.akarmoon.mosquemarker.model.Marker
import com.akarmoon.mosquemarker.ui.viewmodel.MarkerViewModel

class AddMarkerFragment : Fragment() {
    private val navigationArgs: MarkerDetailFragmentArgs by navArgs()
    private lateinit var marker: Marker
    private var _binding: FragmentAddMarkerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarkerViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMarkerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {
            viewModel.getMarker(id).observe(this.viewLifecycleOwner) { selectedItem ->
                marker = selectedItem
                bindMarker(marker)
            }
            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteMarker(marker)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addMarker()
            }
        }
    }

    private fun addMarker() {
        if (isValidEntry()) {
            viewModel.addMarker(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.markerCheckbox.isChecked,
                binding.notesInput.text.toString()
            )
            findNavController().navigate(R.id.action_addMarkerFragment_to_markerListFragment)
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )

    private fun deleteMarker(marker: Marker) {
        viewModel.deleteMarker(marker)
        findNavController().navigate(R.id.action_addMarkerFragment_to_markerListFragment)
    }

    private fun bindMarker(marker: Marker) {
        binding.apply {
            nameInput.setText(marker.name, TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(marker.address, TextView.BufferType.SPANNABLE)
            markerCheckbox.isChecked = marker.isMarker
            notesInput.setText(marker.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateMarker()
            }
        }
    }

    private fun updateMarker() {
        if (isValidEntry()) {
            viewModel.updateMarker(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                address = binding.locationAddressInput.text.toString(),
                isMarker = binding.markerCheckbox.isChecked,
                notes = binding.notesInput.text.toString()
            )
            findNavController().navigate(R.id.action_addMarkerFragment_to_markerListFragment)
        }
    }
}

