package br.com.fiap.affily.ui.child

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.fiap.affily.R
import br.com.fiap.affily.databinding.FragmentItemBinding
import br.com.fiap.affily.ui.base.auth.BaseAuthFragment

import br.com.fiap.affily.ui.child.placeholder.PlaceholderContent.PlaceholderItem


class ReadFragment: BaseAuthFragment() {

    override val layout: Int
        get() = R.layout.fragment_log_in
}