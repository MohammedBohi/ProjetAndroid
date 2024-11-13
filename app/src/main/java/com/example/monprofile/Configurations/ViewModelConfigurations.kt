package com.example.monprofile.Configurations


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monprofile.Retrofit.ApiTMdB
import com.example.monprofile.Retrofit.Filmographie
import com.example.monprofile.Retrofit.MovieCollection
import com.example.monprofile.Retrofit.MovieDetail
import com.example.monprofile.Retrofit.SerieDetail
import com.example.monprofile.Retrofit.TmdbActor
import com.example.monprofile.Retrofit.TmdbMovie
import com.example.monprofile.Retrofit.TmdbSerie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConfigViewModel: ViewModel() {


/*    init {
        getLastMovies()
        getLastSeries()
        getLastActors()
    }*/


    val movieToLook = MutableStateFlow<TmdbMovie>(TmdbMovie())
    val collections = MutableStateFlow<List<MovieCollection>>(listOf())

    val serieToLook = MutableStateFlow<TmdbSerie>(TmdbSerie())
    val actorToLook = MutableStateFlow<TmdbActor>(TmdbActor())

    val filmographieToLook = MutableStateFlow<Filmographie>(Filmographie())
    val actorDetails = MutableStateFlow<TmdbActor?>(null)



    val retrof=  Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrof.create(ApiTMdB::class.java)

    val api_key = "317519a83cc36ab9367ba50e5aa75b40"

    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())

    val series = MutableStateFlow<List<TmdbSerie>>(listOf())

    val actors = MutableStateFlow<List<TmdbActor>>(listOf())

    val moviedetails = MutableStateFlow<MovieDetail>(MovieDetail())

    val seriedetails = MutableStateFlow<SerieDetail>(SerieDetail())







    fun getLastMovies() {
        viewModelScope.launch {
            movies.value = api.lastmovies(api_key).results
        }
    }

    fun getLastSeries() {
        viewModelScope.launch {
            series.value = api.lastseries(api_key).results
        }
    }

    fun getLastActors() {
        viewModelScope.launch {
            actors.value = api.lastpersons(api_key).results
        }
    }

    fun searchFilm(searchText : String) {
        viewModelScope.launch {
            movies.value = api.searchmovies(api_key, searchText).results

        }
    }
    fun searchSerie(searchText : String) {
        viewModelScope.launch {
            series.value = api.searchseries(api_key, searchText).results
        }
    }
    fun searchActor(searchText : String) {
        viewModelScope.launch {
            actors.value = api.searchpersons(api_key, searchText).results
        }
    }
    fun fetchHorrorCollections() {
        viewModelScope.launch {
            try {
                // Appel à l'API pour chercher les collections avec "horreur"
                val response = api.searchCollections(api_key, searchText = "horreur")
                // Mise à jour de l'état avec les résultats
                collections.value = response.results
            } catch (e: Exception) {
                e.printStackTrace() // Log ou gestion de l'erreur
            }
        }
    }

    fun searchMovieDetails(id : String) {
        viewModelScope.launch {
            moviedetails.value = api.movieDetails(id=id,api_key)
        }
    }

    fun searchSerieDetails(id : String) {
        viewModelScope.launch {
            seriedetails.value = api.serieDetails(id=id,api_key)
        }
    }

    fun searchActorDetails(actorId: String) {
        viewModelScope.launch {
            try {
                val response = api.actorDetails(actorId, api_key)
                actorDetails.value = response
            } catch (e: Exception) {
                e.printStackTrace() // Gérer l'erreur ici
            }
        }
    }

}