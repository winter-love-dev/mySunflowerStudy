package com.example.mysunflowerstudy.fragment.plant

import android.view.*
import androidx.fragment.app.viewModels
import com.example.mysunflowerstudy.R
import com.example.mysunflowerstudy.adapter.PlantAdapter
import com.example.mysunflowerstudy.databinding.FragmentPlantListBinding
import com.example.mysunflowerstudy.fragment.base.BaseFragment2
import com.example.mysunflowerstudy.viewmodel.PlantListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantListFragment: BaseFragment2<FragmentPlantListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_plant_list

    private val viewModel: PlantListViewModel by viewModels()

    override fun initAfterView() {
        context ?: return

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_plant_list, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.filter_zone -> {
            updateData()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun subscribeUi(adapter: PlantAdapter) =
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }

    private fun updateData() = with(viewModel) {
        if (isFiltered())
            clearGrowZoneNumber()
        else
            setGrowZoneNumber(9)
    }
}