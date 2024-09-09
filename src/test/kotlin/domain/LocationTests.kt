package domain

import br.dev.ardc.kotlinsandbox.domain.Address
import br.dev.ardc.kotlinsandbox.domain.Location
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocationTests {
    @Test
    fun `a Location is defined by its latitude and longitude`(): Unit {
        // instantiating a class, note the lack of "new"
        val location = Location(1.0, 2.0)
        assert(location.latitude == 1.0)
        assert(location.longitude == 2.0)
    }

    @Test
    fun `a Location created without parameters should default to (0,0)`(): Unit {
        val location = Location()
        assert(location.latitude == 0.0)
        assert(location.longitude == 0.0)
    }

    @Test
    fun `a Location can only be created with valid latitudes`(): Unit {
        assertThrows<IllegalArgumentException> {
            Location(91.0, 0.0)
        }
    }

    @Test
    fun `a Location can only be created with valid longitudes`(): Unit {
        assertThrows<IllegalArgumentException> {
            Location(0.0, 181.0)
        }
    }
}

class AddressTests {
    @Test
    fun `an Address always requires a streetName`(): Unit {
        assertThrows<IllegalArgumentException> {
            Address("")
        }
    }

    @Test
    fun `an Address should be copyable into a new instance`(): Unit {
        val address = Address("street")
        val newAddress = address.copy(
            referencePoint = "reference"
        )
        assert(address.referencePoint == "")
        assert(newAddress.streetName == "street")
        assert(newAddress.referencePoint == "reference")
    }
}