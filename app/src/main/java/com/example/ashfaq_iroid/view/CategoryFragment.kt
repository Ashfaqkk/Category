package com.example.ashfaq_iroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ashfaq_iroid.R
import com.example.ashfaq_iroid.adapter.AdapterCategoryList
import com.example.ashfaq_iroid.adapter.CategoryItemClick
import com.example.ashfaq_iroid.model.CategoryListResponseModel
import com.example.ashfaq_iroid.model.ResultData
import com.example.ashfaq_iroid.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.progress_layout.*


class CategoryFragment : Fragment(),CategoryItemClick {

    private val mViewModel: CategoryViewModel by viewModels()

    private lateinit var mCategoryAdapter: AdapterCategoryList
    var mLoadedCategories: ArrayList<CategoryListResponseModel.Category?> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
        categories()

    }

    private fun initialize() {

        mCategoryAdapter = AdapterCategoryList(requireContext(), this, mLoadedCategories)
        rvAllCategory.adapter = mCategoryAdapter
    }


    private fun categories() {
        mLoadedCategories.clear()
        val repository =
            mViewModel.categories()
        repository.observe(viewLifecycleOwner) { categoryData ->

            when (categoryData) {
                is ResultData.Loading -> {
                    flProgress.visibility = View.VISIBLE
                    tvNoDataFound.visibility = View.GONE
                }
                is ResultData.Success -> {
                    //valid data is available
                    flProgress.visibility = View.GONE

                    if (!categoryData.data?.categories!!.isNullOrEmpty())
                    {
                        mLoadedCategories.addAll(categoryData.data.categories)
                        mCategoryAdapter.notifyDataSetChanged()
                        tvNoDataFound.visibility = View.GONE
                    }
                    else
                    {
                        tvNoDataFound.visibility = View.VISIBLE
                    }


                }
                is ResultData.Failed -> {
                    //can add alert based on there error passed from server
                    flProgress.visibility = View.GONE
                    tvNoDataFound.visibility = View.GONE
                }
                is ResultData.Exception -> {
                    //errors such as http 500 can  be manipulated and displayed
                    flProgress.visibility = View.GONE
                    tvNoDataFound.visibility = View.GONE
                }
                else -> {
                    flProgress.visibility = View.VISIBLE
                    tvNoDataFound.visibility = View.GONE
                }
            }
        }

    }

    override fun onCategoryItemClick(position: Int) {

       Toast.makeText(requireContext(), mLoadedCategories[position].toString(), Toast.LENGTH_SHORT).show()
    }
}