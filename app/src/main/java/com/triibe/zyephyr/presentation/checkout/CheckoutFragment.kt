package com.triibe.zyephyr.presentation.checkout

import android.content.DialogInterface
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.triibe.zyephyr.R
import com.triibe.zyephyr.databinding.FragmentCheckoutBinding
import com.triibe.zyephyr.databinding.FragmentStoreListBinding
import com.triibe.zyephyr.presentation.product_lst.ProductListFragmentDirections
import com.triibe.zyephyr.presentation.product_lst.ProductListState
import com.triibe.zyephyr.presentation.store_lst.StoreListAdapter
import com.triibe.zyephyr.presentation.store_lst.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CheckoutFragment : Fragment() {

    private val viewModel: CheckoutViewModel by viewModels()
    private val searchAdapter = CheckOutAdapter()
    var total = 0


    private val args: CheckoutFragmentArgs by navArgs()

    private var _binding: FragmentCheckoutBinding? = null
    val binding: FragmentCheckoutBinding
        get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        args.products?.let { viewModel.setCart(it) }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.apply {
            adapter = searchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.orderState.collect {
                if (it.isLoading) {
//                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
//                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

//                    if (it.isEmpty()) {
//                        binding.nothingFound.visibility = View.VISIBLE
//                    }
                    binding.progressMealSearch.visibility = View.GONE

                    MaterialAlertDialogBuilder(requireContext()).setTitle("Order Placed").setMessage("Done").setPositiveButton("Go Back",object:DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            findNavController().navigateUp()
                            findNavController().navigateUp()

                        }
                    }).show()
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
                if (it.isEmpty()) {
                }else{
                    searchAdapter.setContentList(it.toMutableList())
                    total = 0
                    it.forEach {
                        total+=(it.price*it.quantity)
                    }
                    binding.tvTotal.text = "$ $total.00"

                }
            }

        }
//        viewModel.cart.value?.data?.let { searchAdapter.setContentList(it) }


        searchAdapter.itemClickListener {
            viewModel.setCart(ProductListState(data = it))
//            findNavController().navigate(
//                StoreListFragmentDirections.actionStoreListFragmentToProductListFragment()
//            )
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAddToCart.setOnClickListener{
//            findNavController().navigate(
//                ProductListFragmentDirections.actionProductListFragmentToCheckoutFragment(viewModel.cart.value)
//            )
            viewModel.placeOrder(binding.edtAddress.text.toString(),total)

        }
    }


}