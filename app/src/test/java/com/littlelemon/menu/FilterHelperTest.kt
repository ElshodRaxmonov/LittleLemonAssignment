package com.littlelemon.menu

import com.littlelemon.menu.helper.FilterHelper
import com.littlelemon.menu.helper.util.FilterType
import com.littlelemon.menu.ui.ProductItem
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterHelperTest {
    @Test
    fun filterProducts_filterTypeDessert_croissantReturned() {
        val filterHelper = FilterHelper()
        val sampleProductsList = mutableListOf(
            ProductItem(
                title = "Black tea",
                price = 3.00,
                category = "Drinks",
                R.drawable.black_tea
            ),
            ProductItem(
                title = "Croissant",
                price = 7.00,
                category = "Dessert",
                R.drawable.croissant
            ),
            ProductItem(
                title = "Bouillabaisse",
                price = 20.00,
                category = "Food",
                R.drawable.bouillabaisse
            )
        )
        val filteredProducts = filterHelper.filterProducts(FilterType.Dessert, sampleProductsList)
        assertEquals(
            ProductItem(
                title = "Croissant",
                price = 7.00,
                category = "Dessert",
                R.drawable.croissant
            ), filteredProducts[0])

    }

}