package com.gmail.sparknetworksgallerytest.data.repository

import android.net.Uri
import com.gmail.sparknetworksgallerytest.data.EMPTY_STRING
import com.gmail.sparknetworksgallerytest.data.IMAGE_FOLDER_STORAGE
import com.gmail.sparknetworksgallerytest.data.SLASH_CHAR
import com.gmail.sparknetworksgallerytest.data.USERS_LINKS_PATH
import com.gmail.sparknetworksgallerytest.data.common.applyIoScheduler
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.entity.ErrorState
import com.gmail.sparknetworksgallerytest.domain.repository.GalleryRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Observable
import io.reactivex.Single

class GalleryRepositoryImpl(private val storage: FirebaseStorage,
                            private val database: FirebaseDatabase,
                            private val auth: FirebaseAuth) : GalleryRepository {

    override fun uploadImage(filePath: Uri): Single<ResultState<Boolean>> {
        return Single.create<ResultState<Boolean>> { subscriber ->
            val userId = auth.currentUser?.uid ?: EMPTY_STRING
            val storageRef = storage.reference
            val dataBaseRef = database.reference

            val childRef = storageRef.child(IMAGE_FOLDER_STORAGE +
                    userId + SLASH_CHAR + getFileName(filePath))
            val uploadTask = childRef.putFile(filePath)
            uploadTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    childRef.downloadUrl.addOnCompleteListener {
                        dataBaseRef.child(USERS_LINKS_PATH)
                                .child(userId)
                                .child(dataBaseRef.push().key ?: EMPTY_STRING)
                                .setValue(it.result?.toString())
                        subscriber.onSuccess(ResultState.Success(true))
                    }
                } else {
                    subscriber.onSuccess(ResultState.Error(
                            ErrorState(0, it.exception?.localizedMessage.toString()), false))
                }
            }
        }.applyIoScheduler()
    }

    override fun getAllUserImagesLinks(): Observable<ResultState<List<String>>> {
        return Observable.create<ResultState<List<String>>> { subscriber ->
            val userId = auth.currentUser?.uid ?: EMPTY_STRING
            val dataBaseRef = database.reference

            dataBaseRef.child(USERS_LINKS_PATH).child(userId)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            subscriber.onNext(ResultState.Error(ErrorState(0, p0.message), null))
                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            val links = p0.value as Map<String, String>
                            subscriber.onNext(ResultState.Success(links.values.toList()))
                        }
                    })
        }.applyIoScheduler()
    }

    private fun getFileName(file: Uri): String {
        val index = file.path?.lastIndexOf(SLASH_CHAR)
        return if (index != -1 && index != null) {
            file.path?.substring(index + 1) ?: EMPTY_STRING
        } else {
            EMPTY_STRING
        }
    }
}