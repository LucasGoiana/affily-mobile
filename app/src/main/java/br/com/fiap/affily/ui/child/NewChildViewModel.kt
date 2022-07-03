package br.com.fiap.affily.ui.child

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Child
import br.com.fiap.affily.models.entities.Children
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NewChildViewModel: ViewModel() {
    private var mAuth: FirebaseAuth =
        FirebaseAuth.getInstance()

    val newChildState = MutableLiveData<RequestState<Children>>()

    private val db = FirebaseFirestore.getInstance()

    fun saveNewChild(nomeCrianca: String) {
        var child = Children(idPais = mAuth.currentUser?.uid ?: "", nome = nomeCrianca)


        db.collection("children")
            .add(child)
            .addOnSuccessListener { documentReference ->
                newChildState.value =
                    RequestState.Success(child)
            }
            .addOnFailureListener { e ->
                newChildState.value = RequestState.Error(
                    Throwable(e.message)
                )
            }
    }



}