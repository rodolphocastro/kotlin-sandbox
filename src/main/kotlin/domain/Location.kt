package br.dev.ardc.kotlinsandbox.domain

/**
 * a representation of a location.
 * @param latitude the latitude of the location
 * @param longitude the longitude of the location
 */
open class Location(val latitude: Double, val longitude: Double) {

    /**
     * initialization logic for a Location.
     */
    init {
        require(latitude in -90.0..90.0) { "latitude must be between -90 and 90" }
        require(longitude in -180.0..180.0) { "longitude must be between -180 and 180" }
    }

    /**
     * special constructor for a blank location.
     */
    constructor() : this(0.0, 0.0)

    /**
     * calculates the distance, in double, between this location and another one.
     */
    fun distanceTo(otherLocation: Location): Double {

        val earthRadius = 6371e3 // Earth radius in meters

        val lat1 = Math.toRadians(this.latitude)
        val lat2 = Math.toRadians(otherLocation.latitude)
        val deltaLat = Math.toRadians(otherLocation.latitude - this.latitude)
        val deltaLon = Math.toRadians(otherLocation.longitude - this.longitude)

        val a =
            Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(
                deltaLon / 2
            )
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return earthRadius * c
    }
}

/**
 * representation of an address, optional when creating a sighting.
 */
data class Address(
    val streetName: String,
    val referencePoint: String = "",
) {
    init {
        require(streetName.isNotBlank()) { "streetName must not be blank" }
    }
}

/**
 * a location that has been decorated with an address.
 */
class DecoratedLocation(baseLocation: Location, val address: Address) :
    Location(baseLocation.latitude, baseLocation.longitude) {

    companion object {
        /**
         * Special Street Name used for empty addresses.
         */
        const val SPECIAL_STREET_NAME = "Placeholder Avenue"

        /**
         * creates a DecoratedLocation from a Location without a placeholder Address.
         */
        fun fromLocation(location: Location): DecoratedLocation {
            return DecoratedLocation(location, Address(SPECIAL_STREET_NAME))
        }
    }

    val displayName: String
        get() {
            return "[%.2f,%.2f] @ ${address.streetName}".format(latitude, longitude)
        }

}

/**
 * util to hold functions common to all Locations.
 */
object LocationUtil {

    /**
     * reports the distance between two different locations.
     */
    fun distanceBetween(location1: Location, location2: Location): Double {
        return location1.distanceTo(location2)
    }
}