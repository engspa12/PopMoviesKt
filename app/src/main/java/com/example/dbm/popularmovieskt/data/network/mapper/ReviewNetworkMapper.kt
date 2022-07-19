package com.example.dbm.popularmovieskt.data.network.mapper

import com.example.dbm.popularmovieskt.data.network.model.ReviewNetwork
import com.example.dbm.popularmovieskt.data.util.NetworkMapper
import com.example.dbm.popularmovieskt.domain.model.ReviewDomain

class ReviewNetworkMapper : NetworkMapper<ReviewNetwork, ReviewDomain> {
    override fun mapToDomainModel(dto: ReviewNetwork): ReviewDomain {
        return ReviewDomain(
            id = dto.id ?: "No id",
            author = dto.author ?: "No author",
            content = dto.content ?: "No content",
            creationDate = dto.creationDate ?: "No creation date",
            editionDate = dto.editionDate ?: "No edition date"
        )
    }
}