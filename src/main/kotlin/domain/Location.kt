package br.dev.ardc.kotlinsandbox.domain

/**
 * a representation of a location.
 * @param latitude the latitude of the location
 * @param longitude the longitude of the location
 */
class Location(val latitude: Double, val longitude: Double) {

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
}