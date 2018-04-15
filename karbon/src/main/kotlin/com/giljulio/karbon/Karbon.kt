package com.giljulio.karbon

import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KVisibility
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType

@Suppress("UNCHECKED_CAST")
fun <T : Any> KClass<T>.createInstance(): T = recursiveInstantiation<T>(this.starProjectedType) as T

fun <T : Any> recursiveInstantiation(kType: KType): Any? {
    val klass = kType.classifier as KClass<*>

    if (kType.isMarkedNullable) {
        return null
    } else if (kType == String::class.createType()) {
        return ""
    } else if (kType == Boolean::class.createType()) {
        return false
    } else if (kType == Date::class.createType()) {
        return Date()
    } else if (kType.isSubtypeOf(Number::class.createType())) {
        return 0
    } else if (kType.isSubtypeOf(Char::class.createType())) {
        return 0.toChar()
    } else if (kType.isSubtypeOf(List::class.starProjectedType)) {
        return emptyList<Any>()
    } else if (kType.isSubtypeOf(Array<Any>::class.starProjectedType)) {
        return emptyArray<Any>()
    } else if (kType.isSubtypeOf(Map::class.starProjectedType)) {
        return emptyMap<Any, Any>()
    } else if (kType.isSubtypeOf(Enum::class.starProjectedType)) {
        return klass.java.enumConstants.first()
    }

    if (klass.isAbstract) {
        throw IllegalArgumentException("Abstract type ${klass.qualifiedName} cannot be instantiated")
    } else if (klass.isCompanion) {
        throw IllegalArgumentException("Companion type ${klass.qualifiedName} cannot be instantiated")
    } else if (klass.visibility != KVisibility.PUBLIC) {
        throw IllegalArgumentException("Visibility of type ${klass.qualifiedName} is not public")
    }

    val constructor = klass.primaryConstructor ?: klass.constructors.first()
    val arguments = constructor.parameters
            .filterNot { it.isOptional }
            .map {
                it to recursiveInstantiation<T>(it.type)
            }
            .toMap()
    return constructor.callBy(arguments)
}