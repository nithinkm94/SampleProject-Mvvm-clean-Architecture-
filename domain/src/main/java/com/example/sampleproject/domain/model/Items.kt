package com.example.sampleproject.domain.model

data class Items (var name : String
) {
    companion object{
        val item = listOf(Items ("1"),
            Items ("2"),
            Items ("3"),
            Items ("4"),
            Items ("5"),
            Items ("6"))
    }
}