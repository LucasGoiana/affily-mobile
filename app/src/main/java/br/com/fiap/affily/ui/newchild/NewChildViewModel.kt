package br.com.fiap.affily.ui.newchild

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Children
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NewChildViewModel: ViewModel() {
    private var mAuth: FirebaseAuth =
        FirebaseAuth.getInstance()

    val newChildState = MutableLiveData<RequestState<Children>>()

    val updateChildState = MutableLiveData<RequestState<Children>>()

    private val db = FirebaseFirestore.getInstance()

    fun saveOrUpdateChild(child: Children) {
        if(child.id !== null){
            update(child)
        }else {
            save(child)
        }

    }
    fun update(child: Children) {



        db.collection("children")
            .document(child.id.toString())
            .set(child)
            .addOnSuccessListener { documentReference ->
                updateChildState.value =
                    RequestState.Success(child)
            }
            .addOnFailureListener { e ->
                updateChildState.value = RequestState.Error(
                    Throwable(e.message)
                )
            }
    }

    fun save(child: Children) {
        var ref = db.collection("children").document()

        var child = Children(id = ref.id, idPais = mAuth.currentUser?.uid ?: "", nome = child.nome)


        db.collection("children")
            .document(child.id.toString())
            .set(child)
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