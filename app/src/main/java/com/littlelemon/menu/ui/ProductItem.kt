package com.littlelemon.menu.ui

import androidx.annotation.DrawableRes

data class ProductItem(
    val title: String,
    val price: Double,
    val category: String,
    @DrawableRes val image: Int
)

data class Products(val items: List<ProductItem>)