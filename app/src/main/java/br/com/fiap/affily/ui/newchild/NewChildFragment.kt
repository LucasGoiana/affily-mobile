package br.com.fiap.affily.ui.newchild

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.fiap.affily.R
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Children
import br.com.fiap.affily.ui.base.auth.BaseAuthFragment

class NewChildFragment : BaseAuthFragment() {

    private lateinit var btSave: Button
    private lateinit var etCrianca: EditText

    private var id: String? = null
    private var idPais: String? = null
    private var nome: String? = null


    override val layout: Int
        get() = R.layout.fragment_new_child

    private val args: NewChildFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        registerObserver()

        if (args.id != null){
            id = args.id
        }
        if (args.idPais != null){
            idPais = args.idPais
        }
        if (args.nome != null){
            nome = args.nome
            etCrianca.setText(nome)
        }

    }
    private val newChildViewModel: NewChildViewModel by viewModels()

    private fun setUpView(view: View) {
        btSave = view.findViewById(R.id.btSave)
        etCrianca = view.findViewById(R.id.etCrianca)


        btSave.setOnClickListener {

            var nomeCrianca = etCrianca.text.toString();

            var child = Children(null,null,nomeCrianca)
            if(id != null){
                child.id = id
                child.idPais = idPais
            }

            newChildViewModel.saveOrUpdateChild(child)
        }
    }

    private fun registerObserver() {
        this.newChildViewModel.newChildState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage("Criança cadastrada com sucesso")
                    findNavController().navigate(R.id.childrenFragment)
                    //ToDo - Voltar para tela de listagem de criança
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
                is RequestState.Loading -> showLoading("Cadastrando a criança")
            }
        })
        this.newChildViewModel.updateChildState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage("Criança atualizada com sucesso!")
                    findNavController().navigate(R.id.childrenFragment)
                    //ToDo - Voltar para tela de listagem de criança
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
                is RequestState.Loading -> showLoading("Atualizando informações da criança")
            }
        })
    }


}

