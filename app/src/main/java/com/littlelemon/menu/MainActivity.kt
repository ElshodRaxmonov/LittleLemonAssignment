package com.littlelemon.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import com.littlelemon.menu.helper.FilterHelper
import com.littlelemon.menu.helper.SortHelper
import com.littlelemon.menu.helper.util.FilterType
import com.littlelemon.menu.helper.util.ProductsWarehouse
import com.littlelemon.menu.helper.util.SortType
import com.littlelemon.menu.ui.ProductItem
import com.littlelemon.menu.ui.Products
import com.littlelemon.menu.ui.ProductsGrid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    private val productsList = ProductsWarehouse.productsList

    private val productsState: MutableStateFlow<Products> =
        MutableStateFlow(Products(productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitUI() }
    }

    @Composable
    fun InitUI() {
        val products by productsState.collectAsState()
        ProductsGrid(products = products, this::startProductActivity)
    }

    private fun startProductActivity(productItem: ProductItem) {

        val intentProductActivity = Intent(this, ProductActivity::class.java)
        intentProductActivity.putExtra(ProductActivity.KEY_IMAGE, productItem.image)
        intentProductActivity.putExtra(ProductActivity.KEY_PRICE, productItem.price)
        intentProductActivity.putExtra(ProductActivity.KEY_TITLE, productItem.title)
        intentProductActivity.putExtra(ProductActivity.KEY_CATEGORY, productItem.category)
        startActivity(intentProductActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)
        if (menu != null) {
            MenuCompat.setGroupDividerEnabled(menu, true)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting) {
            val type = when (item.itemId) {
                R.id.sort_a_z -> SortType.Alphabetically
                R.id.sort_price_asc -> SortType.PriceAsc
                R.id.sort_price_desc -> SortType.PriceDesc
                else -> SortType.Alphabetically
            }
            productsState.update {
                Products(
                    SortHelper().sortProducts(
                        type,
                        productsList
                    )
                )
            }
        } else if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> FilterType.All
                R.id.filter_drinks -> FilterType.Drinks
                R.id.filter_food -> FilterType.Food
                R.id.filter_dessert -> FilterType.Dessert
                else -> FilterType.All
            }
            productsState.update {
                Products(
                    FilterHelper().filterProducts(
                        type,
                        productsList
                    )
                )
            }
        }
        return true
    }
}