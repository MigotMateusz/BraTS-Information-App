package pl.mateuszmigot.brats_information.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage


class CloudStorageRepository {
    var rawImages_t1: MutableMap<String, Bitmap> = mutableMapOf()
    var rawImages_t1c: MutableMap<String, Bitmap> = mutableMapOf()
    var rawImages_t2: MutableMap<String, Bitmap> = mutableMapOf()
    var rawImages_flair: MutableMap<String, Bitmap> = mutableMapOf()
    var segmentedImages: MutableMap<String, Bitmap> = mutableMapOf()
    var expertImages: MutableMap<String, Bitmap> = mutableMapOf()

    private var storage: FirebaseStorage = Firebase.storage
    private var storageRef = storage.reference
    private var rawImagesT1Storage = storageRef.child("raw_images").child("t1")
    private var rawImagesT1cStorage = storageRef.child("raw_images").child("t1c")
    private var rawImagesT2Storage = storageRef.child("raw_images").child("t2")
    private var rawImagesFlairStorage = storageRef.child("raw_images").child("flair")
    private var segmentedImagesStorage = storageRef.child("segmented_images")
    private var expertImagesStorage = storageRef.child("expert_images")
    private val ONE_MEGABYTE: Long = 1024 * 1024;

    fun populateRawImages() {
        rawImagesT1Storage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    rawImagesT1Storage,
                    rawImages_t1
                )
            }
        rawImagesT1cStorage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    rawImagesT1cStorage,
                    rawImages_t1c
                )
            }
        rawImagesT2Storage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    rawImagesT2Storage,
                    rawImages_t2
                )
            }
        rawImagesFlairStorage.listAll()
            .addOnSuccessListener { listResult ->
                iterateOverListOfImagesAndSaveThemToList(
                    listResult,
                    rawImagesFlairStorage,
                    rawImages_flair
                )
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
        images: MutableMap<String, Bitmap>
    ) {
        for (item in listResult.items) {
            val photoReference = storageReference.child(item.name)
            photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
                images[item.name] = bmp
            }
        }
    }
}