package com.giljulio.karbon

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class KarbonTests {

    @Test fun testAllTypes() {
        val user = User::class.createInstance()

        assertEquals(user.forename, "")
        assertEquals(user.surname, null)
        assertEquals(user.pets, emptyList())
        assertEquals(user.location, Continent.EUROPE)
        assertEquals(user.tags, emptyMap())
    }

    data class User(
            val forename: String,
            val surname: String?,
            val dob: Date,
            val pets: List<Pet>,
            val location: Continent,
            val tags: Map<String, String>
    )

    enum class Continent {
        EUROPE, ASIA, AFRICA, NORTH_AMERICA, SOUTH_AMERICA, AUSTRALIA, ANTARCTICA
    }

    data class Pet(val name: String?, val color: Long)

}