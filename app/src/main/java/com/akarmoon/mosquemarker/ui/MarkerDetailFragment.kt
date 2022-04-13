package com.akarmoon.mosquemarker.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akarmoon.mosquemarker.BaseApplication
import com.akarmoon.mosquemarker.R
import com.akarmoon.mosquemarker.databinding.FragmentMarkerDetailBinding
import com.akarmoon.mosquemarker.model.Marker
import com.akarmoon.mosquemarker.ui.viewmodel.MarkerViewModel
import com.akarmoon.mosquemarker.ui.viewmodel.MarkerViewModelFactory


class MarkerDetailFragment : Fragment() {
    private val navigationArgs: MarkerDetailFragmentArgs by navArgs()
    private val viewModel: MarkerViewModel by activityViewModels {
        MarkerViewModelFactory((activity?.application as BaseApplication).database.markerDao())
    }
    private lateinit var marker: Marker
    private var _binding: FragmentMarkerDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.getMarker(id).observe(this.viewLifecycleOwner) { selectedItem ->
            marker = selectedItem
            bindMarker()
        }
    }

    private fun bindMarker() {
        binding.apply {
            name.text = marker.name
            location.text = marker.address
            notes.text = marker.notes
            if (marker.isMarker) {
                isMarker.text = getString(R.string.this_is)
                icBookmark.setImageResource(R.drawable.ic_bookmark_24)
                duaTitle.setText(R.string.title_dua_go_mosque)
                duaContain.setText(R.string.contain_dua_go_mosque)
                duaMean.setText(R.string.mean_dua_go_mosque)
                duaLatin.setText(R.string.latin_dua_go_mosque)
                duaRef.setText(R.string.ref_dua_go_mosque)
            } else {
                icBookmark.setImageResource(R.drawable.ic_bookmark_border_24)
                isMarker.text = getString(R.string.this_is_not)
                duaTitle.setText(R.string.title_dua_leave_home)
                duaContain.setText(R.string.contain_dua_leave_home)
                duaMean.setText(R.string.mean_dua_leave_home)
                duaLatin.setText(R.string.latin_dua_leave_home)
                duaRef.setText(R.string.ref_dua_leave_home)
            }
            editMarkerFab.setOnClickListener {
                findNavController().navigate(
                    MarkerDetailFragmentDirections.actionMarkerDetailFragmentToAddMarkerFragment(
                        marker.id
                    )
                )
            }
            icLocation.setOnClickListener {
                launchMap()
            }
            location.setOnClickListener {
                launchMap()
            }
        }
    }

    private fun launchMap() {
        val address = marker.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}


