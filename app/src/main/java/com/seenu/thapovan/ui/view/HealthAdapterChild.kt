package com.seenu.thapovan.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seenu.thapovan.data.entities.HealthModel
import com.seenu.thapovan.databinding.ItemViewChildBinding
// Adapter class for child view
class HealthAdapterChild(items: List<HealthModel.Data.Health.Accessible>) :
    RecyclerView.Adapter<HealthChildViewHolder>() {

    private var list: List<HealthModel.Data.Health.Accessible> = ArrayList()

    init {
        this.list = items

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthChildViewHolder {
	//init layout binding
        val binding: ItemViewChildBinding =
            ItemViewChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthChildViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HealthChildViewHolder, position: Int) =
        holder.bind(list[position])

}

class HealthChildViewHolder(private val itemBinding: ItemViewChildBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var item: HealthModel.Data.Health.Accessible

    fun bind(item: HealthModel.Data.Health.Accessible) {
        this.item = item
	// binding the data to layout using data binding
        itemBinding.item=item

    }

}
