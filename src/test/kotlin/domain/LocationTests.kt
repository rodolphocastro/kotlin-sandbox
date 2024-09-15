package domain

import br.dev.ardc.kotlinsandbox.domain.Address
import br.dev.ardc.kotlinsandbox.domain.DecoratedLocation
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

    @Test
    fun `distanceTo should return the distance between two different locations`(): Unit {
        val location1 = Location(0.0, 0.0)
        val location2 = Location(0.0, 1.0)
        assert(location1.distanceTo(location2) > 0)

        assert(Location().distanceTo(Location()) == 0.toDouble())
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

class DecoratedLocationTests {

    @Test
    fun `a DecoratedLocation requires a location and an address`(): Unit {
        assertThrows<IllegalArgumentException> {
            DecoratedLocation(Location(), Address(""))
        }
    }

    @Test
    fun `the displayName of a DecoratedLocation should contain its address street as well as its latitude and longitude with 2 points precision`(): Unit {
        val location = Location(1.123456, 2.123456)
        val address = Address("street")
        val decoratedLocation = DecoratedLocation(location, address)
        assert(decoratedLocation.displayName == "[1.12,2.12] @ street")
    }
}