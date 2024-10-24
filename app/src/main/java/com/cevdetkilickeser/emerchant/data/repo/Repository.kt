package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.DataSource
import com.cevdetkilickeser.emerchant.data.entity.cart.Cart
import com.cevdetkilickeser.emerchant.data.entity.cart.CartRequestProduct
import com.cevdetkilickeser.emerchant.data.entity.category.Category
import com.cevdetkilickeser.emerchant.data.entity.like.Like
import com.cevdetkilickeser.emerchant.data.entity.order.Order
import com.cevdetkilickeser.emerchant.data.entity.product.Product
import com.cevdetkilickeser.emerchant.data.entity.profile.Profile
import com.cevdetkilickeser.emerchant.data.entity.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.entity.user.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CopyOnWriteArrayList

class Repository(private val dataSource: DataSource, firebaseDB: FirebaseFirestore) {

    suspend fun login(username: String, password: String): User? =
        dataSource.login(username, password)

    suspend fun getAuthUserProfile(token: String): Profile =
        dataSource.getAuthUserProfile(token)

    suspend fun updateProfile(userId: Int, updateProfileRequest: UpdateProfileRequest): Profile =
        dataSource.updateProfile(userId, updateProfileRequest)

    suspend fun getProducts(): List<Product> =
        dataSource.getProducts()

    suspend fun getCategories(): List<Category> =
        dataSource.getCategories()

    suspend fun getProductsByCategory(category: String): List<Product> =
        dataSource.getProductsByCategory(category)

    suspend fun searchProducts(query: String): List<Product> =
        dataSource.searchProducts(query)

    suspend fun getOrders(userId: String): List<Order> =
        dataSource.getOrders(userId)

    suspend fun getCart(userId: Int): Cart? {
        getFirebaseCartProducts(userId)
        return if (cartRequestProducts.isEmpty()) {
            null
        } else {
            dataSource.getCart(userId, cartRequestProducts)
        }
    }

    // ----------------  Firebase Repo  ---------------- \\

    private val cartRef: CollectionReference = firebaseDB.collection("cart")
    private val cartRequestProducts = CopyOnWriteArrayList<CartRequestProduct>()

    private suspend fun getFirebaseCartProducts(userId: Int) {
        cartRequestProducts.clear()
        val tempCartRequestProduct = mutableListOf<CartRequestProduct>()
        val querySnapshot = cartRef
            .whereEqualTo("userId", userId)
            .get()
            .await()
        if (!querySnapshot.isEmpty) {
            for (document in querySnapshot.documents) {
                val productId = document.getLong("productId")?.toInt() ?: 0
                val quantity = document.getLong("quantity")?.toInt() ?: 0
                val cartProduct = CartRequestProduct(productId, quantity)
                tempCartRequestProduct.add(cartProduct)
            }
            cartRequestProducts.addAll(tempCartRequestProduct.sortedBy { it.productId })
        }
    }

    suspend fun onClickAddToCart(userId: Int, productId: Int, onResult: (Boolean) -> Unit) {
        val querySnapshot = checkFirestoreCart(userId, productId)
        if (querySnapshot.isEmpty) {
            addFirestoreCart(userId, productId, onResult)
        } else {
            increaseQuantity(userId, productId, onResult)
        }
    }

    private suspend fun checkFirestoreCart(userId: Int, productId: Int): QuerySnapshot {
        return cartRef
            .whereEqualTo("userId", userId)
            .whereEqualTo("productId", productId)
            .get()
            .await()
    }

    private suspend fun addFirestoreCart(userId: Int, productId: Int, onResult: (Boolean) -> Unit) {
        cartRef
            .add(mapOf("userId" to userId, "productId" to productId, "quantity" to 1))
            .addOnSuccessListener {
                onResult.invoke(true)
            }.addOnFailureListener {
                onResult.invoke(true)
            }
            .await()
    }

    suspend fun increaseQuantity(userId: Int, productId: Int, onResult: (Boolean) -> Unit) {
        val querySnapshot = checkFirestoreCart(userId, productId)
        val document = querySnapshot.documents.first()
        val newQuantity = document.getLong("quantity")?.plus(1) ?: 1
        cartRef.document(document.id).update("quantity", newQuantity)
            .addOnSuccessListener { onResult.invoke(true) }
            .await()
    }

    suspend fun decreaseQuantity(userId: Int, productId: Int) {
        val querySnapshot = checkFirestoreCart(userId, productId)
        val document = querySnapshot.documents.first()
        val quantity = document.getLong("quantity").toString().toInt()
        if (quantity == 1) {
            cartRef.document(document.id).delete()
                .await()
        } else {
            val newQuantity = document.getLong("quantity")?.minus(1) ?: 1
            cartRef.document(document.id).update("quantity", newQuantity)
                .await()
        }
    }

    // ----------------  Room Repo  ---------------- \\

    suspend fun getLikes(userId: String): List<Like> =
        dataSource.getLikes(userId)

    suspend fun checkLike(userId: String, productId: Int): Like =
        dataSource.checkLike(userId, productId)

    suspend fun insertLike(like: Like) =
        dataSource.insertLike(like)

    suspend fun deleteLike(like: Like) =
        dataSource.deleteLike(like)


}