package com.triibe.zyephyr.presentation.product_lst

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.triibe.zyephyr.R
import com.triibe.zyephyr.databinding.FragmentProductListBinding
import com.triibe.zyephyr.databinding.FragmentStoreListBinding
import com.triibe.zyephyr.domain.model.product_list.ProductModel
import com.triibe.zyephyr.presentation.store_lst.StoreListAdapter
import com.triibe.zyephyr.presentation.store_lst.StoreListFragmentDirections
import com.triibe.zyephyr.presentation.store_lst.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModels()

    private val searchAdapter = ProductListAdapter()


    private var _binding: FragmentProductListBinding? = null
    val binding: FragmentProductListBinding
        get() = _binding!!

    private val args: ProductListFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        viewModel.getProductList()

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.storeName?.let {
            binding.itemName.text = it
        }


        binding.rvProducts.apply {
            adapter = searchAdapter
        }



        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.productList.collect {
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
        viewModel.cart.observe(viewLifecycleOwner) {
            if (it.isLoading) {
//                    binding.nothingFound.visibility = View.GONE
//                    binding.progressMealSearch.visibility = View.VISIBLE
            }
            if (it.error.isNotBlank()) {
//                    binding.nothingFound.visibility = View.GONE
//                    binding.progressMealSearch.visibility = View.GONE
//                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            it.data?.let {
//
                if (it.isEmpty()) {
                    binding.btnAddToCart.visibility = View.GONE
                }else{
                    binding.btnAddToCart.visibility = View.VISIBLE
                    binding.btnAddToCart.text = it.size.toString() + " Selected"
                }
            }

        }


        searchAdapter.itemClickListener {
            viewModel.addToCart(it)
//            findNavController().navigate(
//                StoreListFragmentDirections.actionStoreListFragmentToProductListFragment()
//            )
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAddToCart.setOnClickListener{
            findNavController().navigate(
                ProductListFragmentDirections.actionProductListFragmentToCheckoutFragment(viewModel.cart.value)
            )
        }
    }

}