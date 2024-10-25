package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.DataSource
import com.cevdetkilickeser.emerchant.data.model.cart.Cart
import com.cevdetkilickeser.emerchant.data.model.cart.CartRequestProduct
import com.cevdetkilickeser.emerchant.data.model.category.Category
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.data.model.order.Order
import com.cevdetkilickeser.emerchant.data.model.product.Product
import com.cevdetkilickeser.emerchant.data.model.profile.Profile
import com.cevdetkilickeser.emerchant.data.model.profile.UpdateProfileRequest
import com.cevdetkilickeser.emerchant.data.model.user.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    firebaseDB: FirebaseFirestore
) : Repository {

    override suspend fun login(username: String, password: String): User? =
        dataSource.login(username, password)

    override suspend fun getAuthUserProfile(token: String): Profile =
        dataSource.getAuthUserProfile(token)

    override suspend fun updateProfile(
        userId: Int,
        updateProfileRequest: UpdateProfileRequest
    ): Profile = dataSource.updateProfile(userId, updateProfileRequest)

    override suspend fun getProducts(): List<Product> =
        dataSource.getProducts()

    override suspend fun getCategories(): List<Category> =
        dataSource.getCategories()

    override suspend fun getProductsByCategory(category: String): List<Product> =
        dataSource.getProductsByCategory(category)

    override suspend fun searchProducts(query: String): List<Product> =
        dataSource.searchProducts(query)

    override suspend fun getOrders(userId: String): List<Order> =
        dataSource.getOrders(userId)

    override suspend fun getCart(userId: Int): Cart? {
        getFirebaseCartProducts(userId)
        return if (cartRequestProducts.isEmpty()) {
            null
        } else {
            dataSource.getCart(userId, cartRequestProducts)
        }
    }

    override var orderRef: CollectionReference = firebaseDB.collection("order")

    override var cartRef: CollectionReference = firebaseDB.collection("cart")

    override var cartRequestProducts: CopyOnWriteArrayList<CartRequestProduct> =
        CopyOnWriteArrayList<CartRequestProduct>()

    override suspend fun getFirebaseCartProducts(userId: Int) {
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

    override suspend fun onClickAddToCart(
        userId: Int,
        productId: Int,
        onResult: (Boolean) -> Unit
    ) {
        val querySnapshot = checkFirestoreCart(userId, productId)
        if (querySnapshot.isEmpty) {
            addFirestoreCart(userId, productId, onResult)
        } else {
            increaseQuantity(userId, productId, onResult)
        }
    }

    override suspend fun checkFirestoreCart(userId: Int, productId: Int): QuerySnapshot {
        return cartRef
            .whereEqualTo("userId", userId)
            .whereEqualTo("productId", productId)
            .get()
            .await()
    }

    override suspend fun addFirestoreCart(
        userId: Int,
        productId: Int,
        onResult: (Boolean) -> Unit
    ) {
        cartRef
            .add(mapOf("userId" to userId, "productId" to productId, "quantity" to 1))
            .addOnSuccessListener {
                onResult.invoke(true)
            }.addOnFailureListener {
                onResult.invoke(true)
            }
            .await()
    }

    override suspend fun increaseQuantity(
        userId: Int,
        productId: Int,
        onResult: (Boolean) -> Unit
    ) {
        val querySnapshot = checkFirestoreCart(userId, productId)
        val document = querySnapshot.documents.first()
        val newQuantity = document.getLong("quantity")?.plus(1) ?: 1
        cartRef.document(document.id).update("quantity", newQuantity)
            .addOnSuccessListener { onResult.invoke(true) }
            .await()
    }

    override suspend fun decreaseQuantity(userId: Int, productId: Int) {
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

    override suspend fun checkout(userId: Int, cart: Cart, onResult: (Boolean) -> Unit) {
        orderRef
            .add(mapOf("userId" to userId, "cart" to cart))
            .addOnSuccessListener {
                onResult.invoke(true)
            }.addOnFailureListener {
                onResult.invoke(true)
            }
            .await()
    }

    override suspend fun getLikes(userId: String): List<Like> =
        dataSource.getLikes(userId)

    override suspend fun checkLike(userId: String, productId: Int): Like =
        dataSource.checkLike(userId, productId)

    override suspend fun insertLike(like: Like) =
        dataSource.insertLike(like)

    override suspend fun deleteLike(like: Like) =
        dataSource.deleteLike(like)
}
