package com.example.nontonkuy.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nontonkuy.data.source.remote.response.*

@Entity(tableName = "tvshow_entities")
data class TvShowEntity (
        @ColumnInfo(name = "number_of_episodes")
        val numberOfEpisodes: Int? = null,

        @ColumnInfo(name = "type")
        val type: String? = null,

        @ColumnInfo(name = "backdrop_path")
        val backdropPath: String? = null,

        @ColumnInfo(name = "genres")
        val genres: String? = null,

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int? = null,

        @ColumnInfo(name = "number_of_seasons")
        val numberOfSeasons: Int? = null,

        @ColumnInfo(name = "first_air_date")
        val firstAirDate: String? = null,

        @ColumnInfo(name = "overview")
        val overview: String? = null,

        @ColumnInfo(name = "poster_path")
        val posterPath: String? = null,

        @ColumnInfo(name = "original_name")
        val originalName: String? = null,

        @ColumnInfo(name = "vote_average")
        val voteAverage: Double? = null,

        @ColumnInfo(name = "episode_run_time")
        val episodeRunTime: Int? = null,

        @ColumnInfo(name = "isFav")
        var isFav: Boolean = false,

        @ColumnInfo(name = "status")
        val status: String? = null
){
}