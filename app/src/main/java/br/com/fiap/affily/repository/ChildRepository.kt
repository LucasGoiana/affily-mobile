package br.com.fiap.affily.repository

import androidx.lifecycle.LiveData
import br.com.fiap.affily.dao.ChildDAO
import br.com.fiap.affily.models.entities.Child

class ChildRepository (private val childDAO: ChildDAO) {

    val childs: LiveData<List<Child>> = childDAO.getChild()

    suspend fun insert(child: Child){
        childDAO.insert(child)
    }

    suspend fun update(child: Child){
        childDAO.update(child)
    }




}