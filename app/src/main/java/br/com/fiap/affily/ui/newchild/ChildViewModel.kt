package br.com.fiap.affily.ui.newchild

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.affily.models.RequestState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChildViewModel : ViewModel() {
    private var mAuth = FirebaseAuth.getInstance()
    val loginState = MutableLiveData<RequestState<FirebaseUser>>()

    val resetPasswordState = MutableLiveData<RequestState<String>>()

}