package com.littlelemon.menu.helper

import com.littlelemon.menu.helper.util.FilterType
import com.littlelemon.menu.ui.ProductItem

class FilterHelper {

    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Dessert -> productsList.filter { it.category == "Dessert" }
            FilterType.Drinks -> productsList.filter { it.category == "Drinks" }
            FilterType.Food -> productsList.filter { it.category == "Food" }
        }
    }

}