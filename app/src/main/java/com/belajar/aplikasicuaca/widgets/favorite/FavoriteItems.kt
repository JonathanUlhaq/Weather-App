package com.belajar.aplikasicuaca.widgets.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.belajar.aplikasicuaca.models.ModelFavorite.Favorite
import com.belajar.aplikasicuaca.components.FavoriteItem

@Composable
fun FavoriteItems(
    list: List<Favorite>,
    goDetail: (Favorite) -> Unit,
    onDelete: (Favorite) -> Unit
) {
    LazyColumn(content = {
        items(list) { item ->
            FavoriteItem(fav = item,
                goDetail = {
                    goDetail.invoke(item)
                }) {
                onDelete.invoke(item)
            }
        }
    })
}