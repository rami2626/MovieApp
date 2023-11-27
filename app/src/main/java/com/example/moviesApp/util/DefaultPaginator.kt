package com.example.moviesApp.util

import com.example.moviesApp.networking.Resource

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Resource<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        when (result) {
            is Resource.Success -> {
                val items = result.data
                items?.let {
                    currentKey = getNextKey(items)
                    onSuccess(items, currentKey)
                    onLoadUpdated(false)
                }
            }

            is Resource.Error -> {
                onError(result.message)
                onLoadUpdated(false)
            }


            else -> {}
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}