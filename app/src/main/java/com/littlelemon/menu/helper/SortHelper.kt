package com.littlelemon.menu.helper

import com.littlelemon.menu.ui.ProductItem
import com.littlelemon.menu.helper.util.SortType

class SortHelper {

    fun sortProducts(type: SortType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            SortType.Alphabetically -> productsList.sortAlphabetically()
            SortType.PriceAsc -> productsList.sortPriceAsc()
            SortType.PriceDesc -> productsList.sortPriceDesc()
        }
    }

    private fun List<ProductItem>.sortAlphabetically(): List<ProductItem> {
        return sortedBy { it.title }
    }


    private fun List<ProductItem>.sortPriceAsc(): List<ProductItem> {
        return sortedBy { it.price }
    }

    private fun List<ProductItem>.sortPriceDesc(): List<ProductItem> {
        return sortedByDescending { it.price }

    }
}