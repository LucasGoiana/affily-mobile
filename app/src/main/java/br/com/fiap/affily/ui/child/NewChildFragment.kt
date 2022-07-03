package br.com.fiap.affily.ui.child

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.affily.R
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Child
import br.com.fiap.affily.models.entities.Children
import br.com.fiap.affily.ui.base.auth.BaseAuthFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewChildFragment : BaseAuthFragment() {

    private lateinit var btSave: Button
    private lateinit var etCrianca: EditText

    override val layout: Int
        get() = R.layout.fragment_new_child

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        registerObserver()
    }
    private val newChildViewModel: NewChildViewModel by viewModels()

    private fun setUpView(view: View) {
        btSave = view.findViewById(R.id.btSave)
        etCrianca = view.findViewById(R.id.etCrianca)


        btSave.setOnClickListener {
            var nomeCrianca = etCrianca.text.toString();
            newChildViewModel.saveNewChild(nomeCrianca)
        }
    }

    private fun registerObserver() {
        this.newChildViewModel.newChildState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage("Criança cadastrada com sucesso")
                    R.layout.child_item
                    //ToDo - Voltar para tela de listagem de criança
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
                is RequestState.Loading -> showLoading("Cadastrando a criança")
            }
        })
    }


}

