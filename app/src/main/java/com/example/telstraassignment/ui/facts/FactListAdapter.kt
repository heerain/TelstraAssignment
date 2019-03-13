package com.example.telstraassignment.ui.facts

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.telstraassignment.R
import com.example.telstraassignment.databinding.ItemFactBinding;
import com.example.telstraassignment.model.FactsResponse
import com.squareup.picasso.Picasso


class FactListAdapter : RecyclerView.Adapter<FactListAdapter.ViewHolder>() {

    private lateinit var factList:List<FactsResponse>

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding: ItemFactBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_fact, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::factList.isInitialized) factList.size else 0    }

    override fun onBindViewHolder(p0: FactListAdapter.ViewHolder, p1: Int) {
        p0.bind(factList[p1])
    }

    fun updateFactList(factList:List<FactsResponse>){
        this.factList = factList
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemFactBinding):RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FactViewModel()

        fun bind(factsReponse : FactsResponse){
            viewModel.bind(factsReponse)
            binding.viewModel = viewModel
        }
    }
}