package com.example.telstraassignment.model

import android.arch.persistence.room.Entity


@Entity
data class FactsResponse(
    var title : String,
    var description : String,
    var imageHref : String)