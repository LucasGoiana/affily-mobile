package br.com.fiap.affily.ui.child

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Children
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChildrenViewModel: ViewModel() {


    private var mAuth: FirebaseAuth =
        FirebaseAuth.getInstance()




    val newChildState = MutableLiveData<RequestState<Children>>()

    private val db = FirebaseFirestore.getInstance()

    val getListLiveData: MutableLiveData<List<Children>> by lazy {
        MutableLiveData<List<Children>>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    fun getList() {
        val docRef = db.collection("children")
        docRef.get().addOnSuccessListener {
            val childs = ArrayList<Children>()
            for (item in it.documents) {
                val child = Children(null,null,null)
                child.id = item.data!!["id"] as String
                child.idPais = item.data!!["idPais"] as String
                child.nome = item.data!!["nome"] as String
                childs.add(child)
            }

            getListLiveData.postValue(childs)
        }.addOnFailureListener {
            Log.d("get", it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }

    fun delete(id: String) {
        val docRef = db.collection("children")
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete", it.localizedMessage!!)
            deleteLiveData.postValue(false)
        }
    }



}