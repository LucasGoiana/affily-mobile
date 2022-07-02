package br.com.fiap.affily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.affily.databinding.ChildItemBinding
import br.com.fiap.affily.models.entities.Child

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>(){
    inner class ViewHolder(val  biding: ChildItemBinding) :
            RecyclerView.ViewHolder(biding.root)

    private var child = emptyList<Child>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val biding = ChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(biding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            biding.tvProduct.text = child[position].nome
        }
    }

    fun setChild(childs: List<Child>){
        this.child = childs
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return child.size
    }
}