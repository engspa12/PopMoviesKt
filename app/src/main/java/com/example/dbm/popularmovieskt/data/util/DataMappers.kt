package com.example.dbm.popularmovieskt.data.util

interface NetworkMapper<Dto, DomainModel> {
    fun mapToDomainModel(dto: Dto): DomainModel
}

interface CacheMapper<Dto, DomainModel> {
    fun mapToDomainModel(dto: Dto): DomainModel
    fun mapFromDomainModel(domainModel: DomainModel): Dto
}