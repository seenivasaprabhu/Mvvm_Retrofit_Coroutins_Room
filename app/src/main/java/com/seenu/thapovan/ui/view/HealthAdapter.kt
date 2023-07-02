package com.seenu.thapovan.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seenu.thapovan.data.entities.HealthModel
import com.seenu.thapovan.databinding.ItemViewBinding

//Adapter class for records
class HealthAdapter(context: Context?) : RecyclerView.Adapter<HealthViewHolder>() {

    private var items: ArrayList<HealthModel.Data.Health> = ArrayList()
    private var context=context
    init {
        this.context=context
    }
	//set list to adapter
    fun setItems(items: List<HealthModel.Data.Health>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
	// init layout using binding
        val binding: ItemViewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) =
        holder.bind(items[position],this.context)

}

class HealthViewHolder(private val itemBinding: ItemViewBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var items: HealthModel.Data.Health
	// bind data to view
    fun bind(item: HealthModel.Data.Health, context: Context?) {
        this.items = item
        itemBinding.txtHeader.text = this.items.name
	// Init child adapter and send list to child adapter
        val childAdapter = HealthAdapterChild(this.items.accessible)
        itemBinding.rvChild.layoutManager = LinearLayoutManager(context)
        itemBinding.rvChild.adapter = childAdapter


    }

}
