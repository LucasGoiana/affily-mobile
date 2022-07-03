package br.com.fiap.affily.ui.child

import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.fiap.affily.R
import br.com.fiap.affily.ui.base.auth.BaseAuthFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChildrenFragment: BaseAuthFragment() {

    private lateinit var btAddNewChild: FloatingActionButton

    override val layout: Int
        get() = R.layout.fragment_children

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        //registerObserver()
    }

    private val childrenViewModel: ChildrenViewModel by viewModels()


    private fun setUpView(view: View) {
        btAddNewChild = view.findViewById(R.id.btAddNewChild)


        btAddNewChild.setOnClickListener {
            findNavController().navigate(R.id.newChildFragment)
        }
    }
}