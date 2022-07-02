package br.com.fiap.affily

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.fiap.affily.dao.ChildRoomDatabase
import br.com.fiap.affily.models.RequestState
import br.com.fiap.affily.models.entities.Child
import br.com.fiap.affily.repository.ChildRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val childRepository: ChildRepository

    val childs : LiveData<List<Child>>
    init {
        val childDAO = ChildRoomDatabase.getDatabase(application).childDAO()
        childRepository = ChildRepository(childDAO);
        childs = childRepository.childs
    }

    fun insert(child: Child) = viewModelScope.launch(Dispatchers.IO) {
        childRepository.insert(child)
    }

    fun update(child: Child) = viewModelScope.launch(Dispatchers.IO) {
        childRepository.update(child)
    }

}