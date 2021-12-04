package pl.mateuszmigot.brats_information.repositories

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pl.mateuszmigot.brats_information.models.Model

class FirestoreRepository {
    private val db = Firebase.firestore
    var models: MutableList<Model> = mutableListOf()

    fun getMyModels() {
        models.clear()
        db.collection("mymodels")
            .get()
            .addOnSuccessListener { result ->
                models.clear()
                for (document in result) {
                    val model = document.toObject(Model::class.java)
                    models.add(model)
                    Log.d("Firestore data ->", document.data.toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore error ->", exception)
            }
    }
}