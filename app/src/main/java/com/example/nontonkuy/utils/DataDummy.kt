package com.example.nontonkuy.utils

import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.TvShowEntity
import com.example.nontonkuy.data.source.remote.response.*

object DataDummy {
    fun getMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                464052,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Wonder Woman 1984",
                "Fantasy, Action, Adventure ",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "2020-12-16",
                151,
                200000000,
                154600000,
                7.0,
                false
            ),
            MovieEntity(
                464052,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Wonder Woman 1984",
                "Fantasy, Action, Adventure ",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "2020-12-16",
                151,
                200000000,
                154600000,
                7.0,
                false
            ),MovieEntity(
                464052,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Wonder Woman 1984",
                "Fantasy, Action, Adventure ",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "2020-12-16",
                151,
                200000000,
                154600000,
                7.0,
                false
            )

        )
    }

    fun getDetailMovie(): MovieEntity {
        return MovieEntity(
            464052,
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "Wonder Woman 1984",
            "Fantasy, Action, Adventure ",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "2020-12-16",
            151,
            200000000,
            154600000,
            7.0,
            false
        )
    }

    fun getRemoteMovies(): List<ResultsItemListMovie> {
        return listOf(
            ResultsItemListMovie(
                adult = false,
                backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                genreIds = listOf(14, 28, 12),
                id = 464052,
                originalLanguage = "en",
                originalTitle = "Wonder Woman 1984",
                overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                popularity = 3342.686,
                posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                releaseDate = "2020-12-16",
                title = "Wonder Woman 1984",
                video = false,
                voteAverage = 7.2,
                voteCount = 2641
            ),
            ResultsItemListMovie(
                adult = false,
                backdropPath = "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                genreIds = listOf(16, 35, 18, 10402, 14),
                id = 508442,
                originalLanguage = "en",
                originalTitle = "Soul",
                overview = "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                popularity = 2849.972,
                posterPath = "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                releaseDate = "2020-12-25",
                title = "Soul",
                video = false,
                voteAverage = 8.4,
                voteCount = 3477
            ),
            ResultsItemListMovie(
                adult = false,
                backdropPath = "/ibwOX4xUndc6E90MYfglghWvO5Z.jpg",
                genreIds = listOf(878, 12),
                id = 517096,
                originalLanguage = "ru",
                originalTitle = "Вратарь Галактики",
                overview = "Cosmoball is a mesmerizing intergalactic game of future played between humans and aliens at the giant extraterrestrial ship hovering in the sky over Earth. A young man with enormous power of an unknown nature joins the team of hot-headed superheroes in exchange for a cure for his mother’s deadly illness. The Four from Earth will fight not only to defend the honor of their home planet in the spectacular game, but to face the unprecedented threat to the Galaxy and embrace their own destiny.",
                popularity = 1893.591,
                posterPath = "/eDJYDXRoWoUzxjd52gtz5ODTSU1.jpg",
                releaseDate = "2020-08-27",
                title = "Cosmoball",
                video = false,
                voteAverage = 5.5,
                voteCount = 59
            )
        )
    }

    fun getRemoteDetailMovie(): ResponseDetailMovie {
        return ResponseDetailMovie(
            adult = false,
            backdropPath = "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            belongsToCollection = BelongsToCollection(
                id = 468552,
                name = "Wonder Woman Collection",
                posterPath = "/8AQRfTuTHeFTddZN4IUAqprN8Od.jpg",
                backdropPath = "/n9KlvCOBFDmSyw3BgNrkUkxMFva.jpg"
            ),
            budget = 200000000,
            genres = listOf(
                GenresItem(
                    id = 14,
                    name = "Fantasy"
                ),
                GenresItem(
                    id = 28,
                    name = "Action"
                ),
                GenresItem(
                    id = 12,
                    name = "Adventure"
                )
            ),
            homepage = "https://www.warnerbros.com/movies/wonder-woman-1984",
            id = 464052,
            imdbId = "tt7126948",
            originalLanguage = "en",
            originalTitle = "Wonder Woman 1984",
            overview = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            popularity = 3342.686,
            posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            productionCompanies = listOf(
                ProductionCompaniesItem(
                    id = 9993,
                    logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                    name = "DC Entertainment",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 174,
                    logoPath = "/ky0xOc5OrhzkZ1N6KyUxacfQsCk.png",
                    name = "Warner Bros. Pictures",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 114152,
                    logoPath = null,
                    name = "The Stone Quarry",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 128064,
                    logoPath = "/13F3Jf7EFAcREU0xzZqJnVnyGXu.png",
                    name = "DC Films",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 507,
                    logoPath = "/z7H707qUWigbjHnJDMfj6QITEpb.png",
                    name = "Atlas Entertainment",
                    originCountry = "US"
                ),
                ProductionCompaniesItem(
                    id = 429,
                    logoPath = "/2Tc1P3Ac8M479naPp1kYT3izLS5.png",
                    name = "DC Comics",
                    originCountry = "US"
                )
            ),
            productionCountries = listOf(
                ProductionCountriesItem(
                    iso31661 = "US",
                    name = "United States of America"
                )
            ),
            releaseDate = "2020-12-16",
            revenue = 131400000,
            runtime = 151,
            spokenLanguages = listOf(
                SpokenLanguagesItem(
                    englishName = "English",
                    iso6391 = "en",
                    name = "English"
                )
            ),
            status = "Released",
            tagline = "A new era of wonder begins.",
            title = "Wonder Woman 1984",
            video = false,
            voteAverage = 7.2.toFloat(),
            voteCount = 2654

        )
    }


    fun getTvShows(): List<TvShowEntity> {
        return listOf(
                TvShowEntity(9,
                        "Miniseries",
                        "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        "Animation, Comedy, Family, Drama",
                        85271,
                        1,
                        "2021-01-15",
                        "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        "WandaVision",
                        8.4,
                        36,
                        false
                ),
                TvShowEntity(9,
                        "Miniseries",
                        "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        "Animation, Comedy, Family, Drama",
                        85271,
                        1,
                        "2021-01-15",
                        "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        "WandaVision",
                        8.4,
                        36,
                        false
                ),
                TvShowEntity(9,
                        "Miniseries",
                        "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        "Animation, Comedy, Family, Drama",
                        85271,
                        1,
                        "2021-01-15",
                        "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        "WandaVision",
                        8.4,
                        36,
                        false
                )
        )
    }

    fun getDetailTvShow(): TvShowEntity {
        return TvShowEntity(
                9,
                "Miniseries",
                "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                "Animation, Comedy, Family, Drama",
                85271,
                1,
                "2021-01-15",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "WandaVision",
                8.4,
                36,
                false
        )
    }

    fun getRemoteTvShows(): List<ResultsItemListTvShow> {
        return listOf(
                ResultsItemListTvShow(
                        backdropPath = "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        firstAirDate = "2021-01-15",
                        genreIds = listOf(10765, 9648,18),
                        id = 85271,
                        name = "WandaVision",
                        originCountry = listOf("US"),
                        originalLanguage = "en",
                        originalName = "WandaVision",
                        overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        popularity = 4381.984,
                        posterPath = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        voteAverage = 8.4,
                        voteCount = 4698

                ),
                ResultsItemListTvShow(
                        backdropPath = "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        firstAirDate = "2021-01-15",
                        genreIds = listOf(10765, 9648,18),
                        id = 85271,
                        name = "WandaVision",
                        originCountry = listOf("US"),
                        originalLanguage = "en",
                        originalName = "WandaVision",
                        overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        popularity = 4381.984,
                        posterPath = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        voteAverage = 8.4,
                        voteCount = 4698

                ),
                ResultsItemListTvShow(
                        backdropPath = "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                        firstAirDate = "2021-01-15",
                        genreIds = listOf(10765, 9648,18),
                        id = 85271,
                        name = "WandaVision",
                        originCountry = listOf("US"),
                        originalLanguage = "en",
                        originalName = "WandaVision",
                        overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        popularity = 4381.984,
                        posterPath = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        voteAverage = 8.4,
                        voteCount = 4698

                )
        )
    }

    fun getRemoteDetailTvShow(): ResponseDetailTvShow {
        return ResponseDetailTvShow(
                backdropPath = "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
                createdBy = listOf(
                        CreatedByItem(
                                id = 123132,
                                creditId = "600b066223d278003d3720ef",
                                name = "Jac Schaeffer",
                                gender = 1,
                                profilePath = null
                        )
                ),
                episodeRunTime = listOf(36,30),
                firstAirDate = "2021-01-15",
                genres = listOf(
                        GenresItemTvShow(
                                id = 10765,
                                name = "Sci-Fi & Fantasy"
                        ),
                        GenresItemTvShow(
                                id = 9648,
                                name = "Mystery"
                        ),
                        GenresItemTvShow(
                                id = 18,
                                name = "Drama"
                        )
                ),
                homepage = "https://www.disneyplus.com/series/wandavision/4SrN28ZjDLwH",
                id = 85271,
                inProduction = true,
                languages = listOf("en"),
                lastAirDate = "2021-01-01",
                lastEpisodeToAir = LastEpisodeToAir(
                        airDate = "2021-01-01",
                        episodeNumber = 10,
                        id = 2529899,
                        name = "December 19",
                        overview = "Old wounds begin to heal at a country club holiday party, but a brutal assault by Kreese's students leads to new betrayals and alliances.",
                        productionCode = "",
                        seasonNumber = 3,
                        stillPath = "/AvnkMnigxqapasWQCFpyXLPdxmG.jpg",
                        voteAverage = 7.5,
                        voteCount = 2
                ),
                name = "Cobra Kai",
                nextEpisodeToAir = null,
                networks = listOf(
                        NetworksItem(
                                name = "Netflix",
                                id = 213,
                                logoPath = "/wwemzKWzjKYJFfCeiB57q3r4Bcm.png",
                                originCountry = ""
                        ),
                        NetworksItem(
                                name = "YouTube Premium",
                                id = 1436,
                                logoPath = "/3p05CgodUb9gPayuliuhawNj1Wo.png",
                                originCountry = "US"
                        )
                ),
                numberOfEpisodes = 30,
                numberOfSeasons = 3,
                originCountry = listOf("US"),
                originalLanguage = "en",
                originalName = "Cobra Kai",
                overview = "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                popularity = 1132.227,
                posterPath = "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                productionCompanies = listOf(
                        ProductionCompaniesItemTvShow(
                                id = 101024,
                                logoPath = null,
                                name = "Hurwitz & Schlossberg Productions",
                                originCountry = "US"
                        ),
                        ProductionCompaniesItemTvShow(
                                id = 11073,
                                logoPath = "/wHs44fktdoj6c378ZbSWfzKsM2Z.png",
                                name = "Sony Pictures Television",
                                originCountry = "US"
                        ),
                        ProductionCompaniesItemTvShow(
                                id = 907,
                                logoPath = "/ca5SWI5uvU985f8Kbb4xc8AmVWH.png",
                                name = "Overbrook Entertainment",
                                originCountry = "US"
                        )
                ),
                productionCountries = listOf(
                        ProductionCountriesItem(
                                iso31661 = "US",
                                name = "United States of America"
                        )
                ),
                seasons = listOf(
                        SeasonsItem(
                                airDate = "2018-05-02",
                                episodeCount = 10,
                                id = 99459,
                                name = "Season 1",
                                overview = "Decades after the tournament that changed their lives, the rivalry between Johnny and Daniel reignites.",
                                posterPath = "/lLrKXnM3WPEtrPCd1aTHT6x3hn.jpg",
                                seasonNumber = 1
                        ),
                        SeasonsItem(
                                airDate = "2019-04-24",
                                episodeCount = 10,
                                id = 120052,
                                name = "Season 2",
                                overview = "Johnny continues building a new life, but a face from his past could disrupt his future. Meanwhile, Daniel opens a Miyagi-Do studio to rival Cobra Kai.",
                                posterPath = "/77kyNXN6yCRjDt9eBMj96vLMx8W.jpg",
                                seasonNumber = 2
                        ),
                        SeasonsItem(
                                airDate = "2021-01-01",
                                episodeCount = 10,
                                id = 160283,
                                name = "Season 3",
                                overview = "With a new sensei at the helm of the Cobra Kai dojo, a three-way feud takes center stage. Old grudges — like Cobra Kai — never die.",
                                posterPath = "/5DfWqh360sjMxqj3Th3DZdnFk3I.jpg",
                                seasonNumber = 3
                        )
                ),
                spokenLanguages = listOf(
                        SpokenLanguagesItemTvShow(
                                englishName = "English",
                                iso6391 = "en",
                                name = "English"
                        )
                ),
                status = "Returning Series",
                tagline = "Cobra Kai never dies.",
                type = "Scripted",
                voteAverage = 8.1,
                voteCount = 1724
        )
    }

}