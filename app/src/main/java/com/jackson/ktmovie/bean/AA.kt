package com.jackson.ktmovie.bean

/**
 * AA  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

data class AA(
    val count: Int,
    val start: Int,
    val total: Int,
    val subjects: List<Subject>,
    val title: String
)

data class Subject(
    val rating: Rating,
    val genres: List<String>,
    val title: String,
    val casts: List<Cast>,
    val durations: List<String>,
    val collect_count: Int,
    val mainland_pubdate: String,
    val has_video: Boolean,
    val original_title: String,
    val subtype: String,
    val directors: List<Director>,
    val pubdates: List<String>,
    val year: String,
    val images: Images,
    val alt: String,
    val id: String
)

data class Cast(
    val avatars: Avatars,
    val name_en: String,
    val name: String,
    val alt: String,
    val id: String
)

data class Avatars(
    val small: String,
    val large: String,
    val medium: String
)

data class Rating(
    val max: Int,
    val average: Int,
    val stars: String,
    val min: Int
)



data class Director(
    val avatars: Avatars,
    val name_en: String,
    val name: String,
    val alt: String,
    val id: String
)

data class Images(
    val small: String,
    val large: String,
    val medium: String
)
