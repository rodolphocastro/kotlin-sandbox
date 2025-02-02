package domain

import br.dev.ardc.kotlinsandbox.domain.Address
import br.dev.ardc.kotlinsandbox.domain.DecoratedLocation
import br.dev.ardc.kotlinsandbox.domain.Location
import br.dev.ardc.kotlinsandbox.domain.LocationUtil
import br.dev.ardc.kotlinsandbox.domain.returnsAOrBFromType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

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

    @Test
    fun `a Location's access type should be either public or private`(): Unit {
        val location = Location(1.0, 2.0, Location.LocationType.PUBLIC)
        val privateLocation = Location(1.0, 2.0, Location.LocationType.PRIVATE)
        assert(location.accessType == Location.LocationType.PUBLIC)
        assert(privateLocation.accessType == Location.LocationType.PRIVATE)
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

    @Test
    fun `fromLocation companion function creates a DecoratedLocation from a Location with an empty address but with the Location's data`(): Unit {
        val location = Location(1.0, 2.0)
        val decoratedLocation = DecoratedLocation.fromLocation(location)
        assert(decoratedLocation.latitude == 1.0)
        assert(decoratedLocation.longitude == 2.0)
        assert(decoratedLocation.address.streetName == DecoratedLocation.SPECIAL_STREET_NAME)
    }

    @Test
    fun `as an implementer of Printable the print method should return something`(): Unit {
        val location = Location(1.0, 2.0)
        val decoratedLocation = DecoratedLocation.fromLocation(location)
        assert(decoratedLocation.print().isNotEmpty())
    }

    @Test
    fun `as an implementer of Printable the defaultString method should equal to its toString by default`(): Unit {
        val location = Location(1.0, 2.0)
        val decoratedLocation = DecoratedLocation.fromLocation(location)
        assert(decoratedLocation.defaultString() == decoratedLocation.toString())
    }

    @Test
    fun `returnsAOrB should return A if the parameter is printable`(): Unit {
        val location = Location(1.0, 2.0)
        val decoratedLocation = DecoratedLocation.fromLocation(location)
        assert(returnsAOrBFromType(decoratedLocation) == "A")
    }

    @Test
    fun `locations should be storable on lists, maps and sets`(): Unit {
        val location = Location(1.0, 2.0)
        val decoratedLocation = DecoratedLocation.fromLocation(location)
        val locationList = listOf(location)
        val locationMap = mapOf(location to decoratedLocation)
        val locationSet = setOf(location)
        assert(locationList.contains(location))
        assert(locationMap.containsKey(location))
        assert(locationSet.contains(location))
    }

    @Test
    fun `locations should be usable within lambda functions`(): Unit {
        val location = DecoratedLocation(Location(1.0, 2.0), Address("street"))
        val extractName = { loc: DecoratedLocation -> loc.displayName }
        assert(extractName(location) == location.displayName)
    }
}

class LocationUtilTests {
    @Test
    fun `distanceBetween location should behave the same as distanceTo between two Locations`(): Unit {
        val location1 = Location(0.0, 0.0)
        val location2 = Location(0.0, 1.0)
        assert(LocationUtil.distanceBetween(location1, location2) == location1.distanceTo(location2))
    }

    @Test
    fun `extractDisplayName should execute its postHandle`(): Unit {
        val location = DecoratedLocation(Location(1.0, 2.0), Address("street"))
        var wasCalled = false
        // lambda that sets the variable to true
        val postHandle = { _: DecoratedLocation -> wasCalled = true }
        assert(LocationUtil.extractDisplayName(location, postHandle).isNotEmpty())
        assertTrue(wasCalled)
        wasCalled = false
        LocationUtil.extractDisplayName(location) { _ -> wasCalled = true }
        assertTrue(wasCalled)
    }
}