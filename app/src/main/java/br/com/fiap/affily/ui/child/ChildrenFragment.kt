package br.com.fiap.affily.ui.child

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.affily.ChildListAdapter
import br.com.fiap.affily.R
import br.com.fiap.affily.models.entities.Children
import br.com.fiap.affily.ui.base.auth.BaseAuthFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChildrenFragment: BaseAuthFragment(), ChildListAdapter.OnItemClickListener {

    private lateinit var btAddNewChild: FloatingActionButton

    override val layout: Int
        get() = R.layout.fragment_children

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        initElement(view)
        initViewModel()
        //registerObserver()
    }


    private lateinit var list: ArrayList<Children>
    private lateinit var rvList: RecyclerView


    private val childrenViewModel: ChildrenViewModel by viewModels()


    private fun initElement(view: View) {
        rvList = view.findViewById(R.id.rvChildren)

        list = ArrayList()


        // Get list
        childrenViewModel.getList()
    }

    private fun initViewModel() {
//        childrenViewModel.createLiveData.observe(this, {
//            onCreate(it)
//        })

//        productViewModel.updateLiveData.observe(this, {
//            onUpdate(it)
//        })

        childrenViewModel.deleteLiveData.observe(viewLifecycleOwner, {
            onDelete(it)
        })


        childrenViewModel.getListLiveData.observe(viewLifecycleOwner) {
            onGetList(it)
        }
    }

    private fun onDelete(it: Boolean) {
        if (it) {
            childrenViewModel.getList()
        }
    }

    private fun onGetList(it: List<Children>) {
        list = ArrayList()
        list.addAll(it)

        var childListAdapter = ChildListAdapter(list, this)

        rvList.adapter = childListAdapter
        rvList.layoutManager = LinearLayoutManager(activity)

        childListAdapter.notifyDataSetChanged()
    }

    private fun setUpView(view: View) {
        btAddNewChild = view.findViewById(R.id.btAddNewChild)


        btAddNewChild.setOnClickListener {
            findNavController().navigate(R.id.newChildFragment)
            val bundle = Bundle()
            bundle.putString("id",null)
            bundle.putString("idPais",null)
            bundle.putString("nome",null)
        }
    }

    override fun onClick(item: Children, position: Int) {
        val bundle = Bundle()
        bundle.putString("id",item.id)
        bundle.putString("idPais",item.idPais)
        bundle.putString("nome",item.nome)
        findNavController().navigate(R.id.action_childrenFragment_to_newChildFragment,bundle)
    }

    override fun onDelete(item: Children, position: Int) {
        childrenViewModel.delete(item.id.toString())
    }

}