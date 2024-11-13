package com.example.monprofile.Retrofit


class TmdbMovieResult(
    var page: Int = 0,
    val results: List<TmdbMovie> = listOf())

class TmdbMovie(
    var overview: String = "",
    val release_date: String = "",
    val id: String = "",
    val title: String = "",
    val original_title: String = "",
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val poster_path: String? = "")


class TmdbSerieResult(
    val page: Int = 0,
    val results: List<TmdbSerie> = listOf(),
    val total_pages: Int =0,
    val total_results: Int = 0
)

class TmdbSerie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val first_air_date: String= "",
    val genre_ids: List<Int> = listOf(),
    val id: Int =0,
    val media_type: String= "",
    val name: String= "",
    val origin_country: List<String> = listOf(),
    val original_language: String= "",
    val original_name: String= "",
    val overview: String= "",
    val popularity: Double =0.0,
    val poster_path: String= "",
    val vote_average: Double =0.0,
    val vote_count: Int=0
)

class TmdbActorResult(
    val page: Int =0,
    val results: List<TmdbActor> = listOf(),
    val total_pages: Int =0,
    val total_results: Int = 0
)

class TmdbActor(
    val adult: Boolean=false,
    val gender: Int=0,
    val id: Int=0,
    val known_for: List<Filmographie> = listOf(),
    val known_for_department: String= "",
    val media_type: String= "",
    val name: String= "",
    val original_name: String= "",
    val popularity: Double=0.0,
    val profile_path: String= ""
)

class Filmographie(
    val adult: Boolean =false,
    val backdrop_path: String= "",
    val first_air_date: String= "",
    val genre_ids: List<Int> = listOf(),
    val id: Int=0,
    val media_type: String= "",
    val name: String= "",
    val origin_country: List<String> = listOf(),
    val original_language: String= "",
    val original_name: String= "",
    val original_title: String= "",
    val overview: String= "",
    val popularity: Double=0.0,
    val poster_path: String= "",
    val release_date: String= "",
    val title: String= "",
    val video: Boolean=false,
    val vote_average: Double=0.0,
    val vote_count: Int=0
)

data class MovieDetail(
    val adult: Boolean =false,
    val backdrop_path: String ="",
    val belongs_to_collection: Any ="",
    val budget: Int =0,
    val credits: Credits = Credits(),
    val genres: List<Genre> = listOf(),
    val homepage: String ="",
    val id: Int =0,
    val imdb_id: String ="",
    val original_language: String ="",
    val original_title: String ="",
    val overview: String ="",
    val popularity: Double =0.0,
    val poster_path: String ="",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String ="",
    val revenue: Int =0,
    val runtime: Int =0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String ="",
    val tagline: String ="",
    val title: String ="",
    val video: Boolean =false,
    val vote_average: Double =0.0,
    val vote_count: Int =0
)

data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf()
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int = 0,
    val logo_path: String? = null,
    val name: String = "",
    val origin_country: String? = null
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

fun Cast.toTmdbActor(): TmdbActor {
    return TmdbActor(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        known_for_department = this.known_for_department,
        name = this.name,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path
    )
}


data class SerieDetail(
    val adult: Boolean = false,
    val backdrop_path: Any ="",
    val created_by: List<Any> = listOf(),
    val credits: Credits = Credits(),
    val episode_run_time: List<Any> =listOf(),
    val first_air_date: String ="",
    val genres: List<Genre> =listOf(),
    val homepage: String ="",
    val id: Int=0,
    val in_production: Boolean =false,
    val languages: List<Any> =listOf(),
    val last_air_date: String ="",
    val last_episode_to_air: LastEpisodeToAir = LastEpisodeToAir(),
    val name: String ="",
    val networks: List<Any> =listOf(),
    val next_episode_to_air: Any="",
    val number_of_episodes: Int=0,
    val number_of_seasons: Int=0,
    val origin_country: List<Any> =listOf(),
    val original_language: String ="",
    val original_name: String ="",
    val overview: String ="",
    val popularity: Double =0.0,
    val poster_path: Any ="",
    val production_companies: List<Any> =listOf(),
    val production_countries: List<Any> =listOf(),
    val seasons: List<Season> =listOf(),
    val spoken_languages: List<Any> =listOf(),
    val status: String ="",
    val tagline: String ="",
    val type: String ="",
    val vote_average: Double =0.0,
    val vote_count: Int =0
)





class LastEpisodeToAir(
    val air_date: String ="",
    val episode_number: Int=0,
    val episode_type: String ="",
    val id: Int=0,
    val name: String ="",
    val overview: String ="",
    val production_code: String ="",
    val runtime: Any ="",
    val season_number: Int =0,
    val show_id: Int =0,
    val still_path: String ="",
    val vote_average: Double =0.0,
    val vote_count: Int =0
)

class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: Any,
    val season_number: Int,
    val vote_average: Double
)
data class KeywordResponse(
    val page: Int,
    val results: List<Keyword>
)

data class Keyword(
    val id: Int,
    val name: String
)


data class MovieCollection(
    val id: Int, // ID unique de la collection
    val name: String, // Nom de la collection
    val overview: String?, // Description
    val poster_path: String?, // Chemin de l'affiche
    val backdrop_path: String? // Chemin de l'image de fond
)



