package edu.nd.pmcburne.hello

class LocationRepository(
    private val api: PlacemarkApiService,
    private val dao: LocationDao
) {
    suspend fun syncLocations() {
        val apiData = api.getPlacemarkLocations()
        dao.insertAll(apiData.map { it.toEntity() })
    }

    suspend fun getAllLocations(): List<LocationEntity> {
        return dao.getAllLocations()
    }
}