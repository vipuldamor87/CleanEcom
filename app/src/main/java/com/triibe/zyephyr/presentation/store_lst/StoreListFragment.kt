package com.triibe.zyephyr.presentation.store_lst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.triibe.zyephyr.R
import com.triibe.zyephyr.databinding.FragmentStoreListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class StoreListFragment : Fragment() {

    private val viewModel: StoreListViewModel by viewModels()

    private val searchAdapter = StoreListAdapter()


    private var _binding: FragmentStoreListBinding? = null
    val binding: FragmentStoreListBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStoreListBinding.inflate(inflater, container, false)
        viewModel.getStoreList("s")

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.apply {
            adapter = searchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.storeList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }


            }
        }

        searchAdapter.itemClickListener {
            findNavController().navigate(
                StoreListFragmentDirections.actionStoreListFragmentToProductListFragment(it.title)
            )
        }

    }

}