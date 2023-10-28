package com.santo.practicacuatro.ui.captura

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.santo.practicacuatro.data.ComplaintRepository
import com.santo.practicacuatro.data.ResourceRemote
import com.santo.practicacuatro.model.Liquor
import kotlinx.coroutines.launch

class CapturaMainViewModel : ViewModel() {

    private val liquorRepository = ComplaintRepository()
    private var liquorsListLocal = mutableListOf<Liquor?>()

    val mensaje5: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _liquorsList:MutableLiveData<MutableList<Liquor?>> = MutableLiveData()
    val liquorsList : LiveData<MutableList<Liquor?>> = _liquorsList

    /*private val _liquorErase:MutableLiveData<Boolean> = MutableLiveData()
    val liquorErase:LiveData<Boolean> = _liquorErase*/

    fun loadLiquors() {
        liquorsListLocal.clear()
        viewModelScope.launch{
            val result = liquorRepository.loadLiquors()
            result.let{resourceRemote->
                when (resourceRemote){
                    is ResourceRemote.Success ->{
                        result.data?.documents?.forEach { document->
                            val liquor = document.toObject<Liquor>()
                            liquorsListLocal.add(liquor)
                        }
                        _liquorsList.postValue(liquorsListLocal)


                    }
                    is ResourceRemote.Error->{
                        var msg = result.message

                        mensaje5.postValue (msg)


                    }
                    else->{
                        //no use
                    }


                }
            }
        }
    }

    /*fun deleteLiquor(liquor: Liquor?) {
        viewModelScope.launch{
            val result = liquorRepository.deleteLicor(liquor)
            result.let{resourceRemote->
                when (resourceRemote){
                    is ResourceRemote.Success ->{
                        _liquorErase.postValue(true)
                        mensaje5.postValue (" licor eliminado ")



                    }
                    is ResourceRemote.Error->{
                        var msg = result.message

                        mensaje5.postValue (msg)


                    }
                    else->{
                        //no use
                    }


                }
            }

        }

    }*/
}