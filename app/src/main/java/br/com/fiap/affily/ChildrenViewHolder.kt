package br.com.fiap.affily

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.affily.models.entities.Children

class ChildrenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
    private val nameText: TextView = itemView.findViewById(R.id.tvProduct)
    fun bindItem(children: Children) {
        itemView.apply {
            nameText.text = children.nome
        }
    }
}
