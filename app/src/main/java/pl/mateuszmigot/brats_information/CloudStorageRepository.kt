package pl.mateuszmigot.brats_information

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class CloudStorageRepository {
    var rawImages: MutableList<Bitmap> = mutableListOf<Bitmap>()
    var segmentedImages: MutableList<Bitmap> = mutableListOf<Bitmap>()
    var expertImages: MutableList<Bitmap> = mutableListOf<Bitmap>()

    private var storage: FirebaseStorage = Firebase.storage
    private var storageRef = storage.reference
    private var rawImagesStorage = storageRef.child("raw_images")
    private var segmentedImagesStorage = storageRef.child("segmented_images")
    private var expertImagesStorage = storageRef.child("expert_images")
    private val ONE_MEGABYTE: Long = 1024 * 1024;

    fun populateRawImages() {
        rawImagesStorage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(listResult, rawImagesStorage, rawImages)
            }
    }

    fun populateSegmentedModelImages() {
        segmentedImagesStorage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    segmentedImagesStorage,
                    segmentedImages
                )
            }
    }

    fun populateExpertImages() {
        expertImagesStorage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    expertImagesStorage,
                    expertImages
                )
            }
    }

    private fun iterateOverListOfImagesAndSaveThemToList(
        listResult: ListResult,
        storageReference: StorageReference,
        images: MutableList<Bitmap>
    ) {
        for (item in listResult.items) {
            val photoReference = storageReference.child(item.name)
            photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                images.add(bmp)
            }
        }
    }
}