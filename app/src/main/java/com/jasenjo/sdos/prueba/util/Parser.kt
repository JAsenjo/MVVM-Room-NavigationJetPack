package com.jasenjo.sdos.prueba.util

import com.jasenjo.sdos.prueba.data.model.FruitModel
import com.jasenjo.sdos.prueba.persistence.entity.FruitEntity

fun fromFruitModelToFruitEntity(fruits: List<FruitModel>): MutableList<FruitEntity> {
    val fruitEntities: MutableList<FruitEntity> = mutableListOf()
    fruits.forEach { fruitModel ->
        fruitEntities.add(FruitEntity(
            farmerId = fruitModel.farmerId,
            category =  fruitModel.category,
            item = fruitModel.item,
            phone = fruitModel.phone))
    }
    return fruitEntities
}