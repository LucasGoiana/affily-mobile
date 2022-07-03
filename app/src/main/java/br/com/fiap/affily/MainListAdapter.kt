package br.com.fiap.affily

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.affily.models.entities.Children

class MainListAdapter(
    var list: List<Children>,
    var onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<ChildrenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
        return ChildrenViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        val item = list[position]
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(item, position)
        }
//        holder.itemView.findViewById<Button>(R.id.delete).setOnClickListener {
//            onItemClickListener.onDelete(item, position)
//        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onClick(item: Children, position: Int)
        fun onDelete(item: Children, position: Int)
    }
}