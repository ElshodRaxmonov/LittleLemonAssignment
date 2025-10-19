package com.littlelemon.menu.helper.util

sealed class SortType {
    object Alphabetically : SortType()
    object PriceAsc : SortType()
    object PriceDesc : SortType()
}