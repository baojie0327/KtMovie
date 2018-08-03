package com.jackson.ktmovie.bean

/**
 * InTheatersBean  2018-07-02
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 02
 */
class HotShowBean : BaseBean<HotShowBean.SubjectsBean>() {


    class SubjectsBean : BasicBean() {
        val rating: Rating? = null
        val genres: List<String>? = null
        val title: String? = null
        val casts: List<Cast>? = null
        val durations: List<String>? = null
        val collect_count: Int = 0
        val mainland_pubdate: String? = null
        val has_video: Boolean = false
        val original_title: String? = null
        val subtype: String? = null
        val directors: List<Director>? = null
        val pubdates: List<String>? = null
        val year: String? = null
        val images: Images? = null
        val alt: String? = null
        val id: String? = null


        class Cast : BasicBean() {
            val avatars: Avatars? = null
            val name_en: String? = null
            val name: String? = null
            val alt: String? = null
            val id: String? = null

            class Avatars : BasicBean() {
                val small: String? = null
                val large: String? = null
                val medium: String? = null
            }
        }

        class Rating : BasicBean() {
            val max: Int = 0
            val average: Float = 0.0f
            val stars: String? = null
            val min: Int? = null
        }

        class Director : BasicBean() {
            val avatars: Avatars? = null
            val name_en: String? = null
            val name: String? = null
            val alt: String? = null
            val id: String? = null

            class Avatars : BasicBean() {
                val small: String? = null
                val large: String? = null
                val medium: String? = null
            }
        }

        class Images : BasicBean() {
            val small: String? = null
            val large: String? = null
            val medium: String? = null
        }


    }


}