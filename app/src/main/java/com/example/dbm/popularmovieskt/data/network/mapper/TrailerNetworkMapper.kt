package com.example.dbm.popularmovieskt.data.network.mapper

import com.example.dbm.popularmovieskt.data.network.model.TrailerNetwork
import com.example.dbm.popularmovieskt.domain.model.TrailerDomain
import com.example.dbm.popularmovieskt.domain.util.NetworkMapper

class TrailerNetworkMapper: NetworkMapper<TrailerNetwork, TrailerDomain> {
    override fun mapToDomainModel(dto: TrailerNetwork): TrailerDomain {
        return TrailerDomain(
            id = dto.id ?: "No id",
            name = dto.name ?: "No name",
            key = dto.key ?: "No key",
            type = dto.type ?: "No type"
        )
    }
}