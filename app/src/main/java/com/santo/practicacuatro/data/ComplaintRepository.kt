package com.santo.practicacuatro.data

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.santo.practicacuatro.model.Complaint
import com.santo.practicacuatro.model.Liquor
import kotlinx.coroutines.tasks.await

class ComplaintRepository {
    private var db = Firebase.firestore
   suspend fun createDenuncia(denuncia: Complaint) :ResourceRemote<String?> {
        return try {
            val document = db.collection("complaints").document()
            denuncia.id = document.id
             db.collection("complaints").document(document.id).set(denuncia).await()
            ResourceRemote.Success(data = document.id)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseFierestoreException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseException){
            Log.e("FirebaseException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }
    }

    suspend fun loadLiquors() :ResourceRemote<QuerySnapshot>{
        return try {
            val result= db.collection("liquor").get().await()


            ResourceRemote.Success(data = result)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseFierestoreException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseException){
            Log.e("FirebaseException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }

    }

    /*suspend fun deleteLicor(liquor: Liquor?): ResourceRemote<String?> {
        return try {
            val result= liquor?.id?.let { db.collection("liquor").document(it).delete().await() }


            ResourceRemote.Success(data = liquor?.id)
        } catch (e: FirebaseAuthException){
            Log.e("FirebaseFierestoreException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.e("FirebaseNetworkException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }catch (e: FirebaseException){
            Log.e("FirebaseException",e.localizedMessage)
            ResourceRemote.Error(message=e.localizedMessage)
        }

    }*/


}