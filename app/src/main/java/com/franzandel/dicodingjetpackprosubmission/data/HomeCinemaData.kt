package com.franzandel.dicodingjetpackprosubmission.data

import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.data.entity.Movie
import com.franzandel.dicodingjetpackprosubmission.data.entity.TvShow

/**
 * Created by Franz Andel on 12/02/21.
 * Android Engineer
 */

object HomeCinemaData {

    fun getAllMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()
        movies.add(
            Movie(
                title = "A Star Is Born",
                desription = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                image = R.drawable.ic_movies_a_star_is_born,
                genre = "Drama, Romance, Music",
                releaseDate = "10/05/2018",
                length = "2h 16m",
                rating = "7.5/10"
            )
        )
        movies.add(
            Movie(
                title = "Alita: Battle Angel",
                desription = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                image = R.drawable.ic_movies_alita,
                genre = "Action, Science Fiction, Adventure",
                releaseDate = "02/14/2019",
                length = "2h 2m",
                rating = "7.1/10"
            )
        )
        movies.add(
            Movie(
                title = "Aquaman",
                desription = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                image = R.drawable.ic_movies_aquaman,
                genre = "Action, Adventure, Fantasy",
                releaseDate = "12/21/2018",
                length = "2h 24m",
                rating = "6.9/10"
            )
        )
        movies.add(
            Movie(
                title = "Bohemian Rhapsody",
                desription = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                image = R.drawable.ic_movies_bohemian,
                genre = "Music, Drama, History",
                releaseDate = "11/02/2018",
                length = "2h 15m",
                rating = "8.0/10"
            )
        )
        movies.add(
            Movie(
                title = "Cold Pursuit",
                desription = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                image = R.drawable.ic_movies_cold_pursuit,
                genre = "Action, Crime, Thriller",
                releaseDate = "02/08/2019",
                length = "1h 59m",
                rating = "5.6/10"
            )
        )
        movies.add(
            Movie(
                title = "Creed II",
                desription = "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                image = R.drawable.ic_movies_creed,
                genre = "Drama",
                releaseDate = "11/21/2018",
                length = "2h 10m",
                rating = "6.9/10"
            )
        )
        movies.add(
            Movie(
                title = "Fantastic Beasts: The Crimes of Grindelwald",
                desription = "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                image = R.drawable.ic_movies_crimes,
                genre = "Adventure, Fantasy, Drama",
                releaseDate = "11/16/2018",
                length = "2h 14m",
                rating = "6.9/10"
            )
        )
        movies.add(
            Movie(
                title = "Glass",
                desription = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                image = R.drawable.ic_movies_glass,
                genre = "Thriller, Drama, Science Fiction",
                releaseDate = "01/18/2019",
                length = "2h 9m",
                rating = "6.6/10"
            )
        )
        movies.add(
            Movie(
                title = "How to Train Your Dragon: The Hidden World",
                desription = "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                image = R.drawable.ic_movies_how_to_train,
                genre = "Animation, Family, Adventure",
                releaseDate = "02/22/2019",
                length = "1h 44m",
                rating = "7.8/10"
            )
        )
        movies.add(
            Movie(
                title = "Avengers: Infinity War",
                desription = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                image = R.drawable.ic_movies_infinity_war,
                genre = "Adventure, Action, Science Fiction",
                releaseDate = "04/27/2018",
                length = "2h 29m",
                rating = "8.3/10"
            )
        )
        movies.add(
            Movie(
                title = "Mary Queen of Scots",
                desription = "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                image = R.drawable.ic_movies_marry_queen,
                genre = "Drama, History",
                releaseDate = "12/21/2018",
                length = "2h 4m",
                rating = "6.6/10"
            )
        )
        movies.add(
            Movie(
                title = "Master Z: Ip Man Legacy",
                desription = "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                image = R.drawable.ic_movies_master_z,
                genre = "Action",
                releaseDate = "12/26/2018",
                length = "1h 47m",
                rating = "5.7/10"
            )
        )
        movies.add(
            Movie(
                title = "Mortal Engines",
                desription = "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                image = R.drawable.ic_movies_mortal_engines,
                genre = "Adventure, Science Fiction",
                releaseDate = "12/14/2018",
                length = "2h 9m",
                rating = "6.1/10"
            )
        )
        movies.add(
            Movie(
                title = "Overlord",
                desription = "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                image = R.drawable.ic_movies_overlord,
                genre = "Horror, War, Science Fiction",
                releaseDate = "11/09/2018",
                length = "1h 50m",
                rating = "6.1/10"
            )
        )
        movies.add(
            Movie(
                title = "Ralph Breaks the Internet",
                desription = "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                image = R.drawable.ic_movies_ralph,
                genre = "Family, Animation, Comedy, Adventure",
                releaseDate = "11/21/2018",
                length = "1h 57m",
                rating = "7.2/10"
            )
        )
        movies.add(
            Movie(
                title = "Robin Hood",
                desription = "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                image = R.drawable.ic_movies_robin_hood,
                genre = "Adventure, Action, Thriller",
                releaseDate = "11/21/2018",
                length = "1h 56m",
                rating = "5.9/10"
            )
        )
        movies.add(
            Movie(
                title = "Serenity",
                desription = "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                image = R.drawable.ic_movies_serenity,
                genre = "Thriller, Mystery, Drama",
                releaseDate = "01/25/2019",
                length = "1h 42m",
                rating = "5.4/10"
            )
        )
        movies.add(
            Movie(
                title = "Spider-Man: Into the Spider-Verse",
                desription = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                image = R.drawable.ic_movies_spiderman,
                genre = "Action, Adventure, Animation, Science Fiction, Comedy",
                releaseDate = "12/14/2018",
                length = "1h 57m",
                rating = "8.4/10"
            )
        )
        movies.add(
            Movie(
                title = "T-34",
                desription = "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                image = R.drawable.ic_movies_t34,
                genre = "War, Action, Drama, History",
                releaseDate = "01/01/2019",
                length = "2h 19m",
                rating = "6.8/10"
            )
        )
        return movies
    }

    fun getAllTvShows(): List<TvShow> {
        val tvShows = mutableListOf<TvShow>()
        tvShows.add(
            TvShow(
                title = "Arrow",
                desription = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                image = R.drawable.ic_tv_shows_arrow,
                genre = "Crime, Drama, Mystery, Action & Adventure",
                releaseYear = "2012",
                length = "42m",
                rating = "6.6/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Doom Patrol",
                desription = "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                image = R.drawable.ic_tv_shows_doom_patrol,
                genre = "Sci-Fi & Fantasy, Action & Adventure, Comedy",
                releaseYear = "2019",
                length = "49m",
                rating = "7.6/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Dragon Ball",
                desription = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                image = R.drawable.ic_tv_shows_dragon_ball,
                genre = "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                releaseYear = "1986",
                length = "25m",
                rating = "8.1/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Fairy Tail",
                desription = "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                image = R.drawable.ic_tv_shows_fairytail,
                genre = "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                releaseYear = "2009",
                length = "25m",
                rating = "7.7/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Family Guy",
                desription = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                image = R.drawable.ic_tv_shows_family_guy,
                genre = "Animation, Comedy",
                releaseYear = "2009",
                length = "22m",
                rating = "6.9/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "The Flash",
                desription = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                image = R.drawable.ic_tv_shows_flash,
                genre = "Drama, Sci-Fi & Fantasy",
                releaseYear = "2014",
                length = "44m",
                rating = "7.6/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Game of Thrones",
                desription = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                image = R.drawable.ic_tv_shows_got,
                genre = "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery, War & Politics",
                releaseYear = "2014",
                length = "1h",
                rating = "8.4/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Gotham",
                desription = "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                image = R.drawable.ic_tv_shows_gotham,
                genre = "Drama, Crime, Sci-Fi & Fantasy",
                releaseYear = "2014",
                length = "43m",
                rating = "7.5/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Grey's Anatomy",
                desription = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                image = R.drawable.ic_tv_shows_grey_anatomy,
                genre = "Drama",
                releaseYear = "2005",
                length = "43m",
                rating = "8.2/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Hanna",
                desription = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                image = R.drawable.ic_tv_shows_hanna,
                genre = "Action & Adventure, Drama",
                releaseYear = "2019",
                length = "50m",
                rating = "7.5/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Marvel's Iron Fist",
                desription = "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                image = R.drawable.ic_tv_shows_iron_fist,
                genre = "Action & Adventure, Drama, Sci-Fi & Fantasy",
                releaseYear = "2017",
                length = "55m",
                rating = "6.5/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Naruto Shippuden the Movie",
                desription = "Demons that once almost destroyed the world, are revived by someone. To prevent the world from being destroyed, the demon has to be sealed and the only one who can do it is the shrine maiden Shion from the country of demons, who has two powers; one is sealing demons and the other is predicting the deaths of humans. This time Naruto's mission is to guard Shion, but she predicts Naruto's death. The only way to escape it, is to get away from Shion, which would leave her unguarded, then the demon, whose only goal is to kill Shion will do so, thus meaning the end of the world. Naruto decides to challenge this \"prediction of death.\"",
                image = R.drawable.ic_tv_shows_naruto_shipudden,
                genre = "Family, Action, Animation, Adventure, Fantasy",
                releaseYear = "2007",
                length = "1h 34m",
                rating = "7.2/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "NCIS",
                desription = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                image = R.drawable.ic_tv_shows_ncis,
                genre = "Crime, Action & Adventure, Drama",
                releaseYear = "2003",
                length = "45m",
                rating = "7.3/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Riverdale",
                desription = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                image = R.drawable.ic_tv_shows_riverdale,
                genre = "Mystery, Drama, Crime",
                releaseYear = "2017",
                length = "45m",
                rating = "8.6/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Shameless",
                desription = "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                image = R.drawable.ic_tv_shows_shameless,
                genre = "Drama, Comedy",
                releaseYear = "2011",
                length = "1h",
                rating = "7.9/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Supergirl",
                desription = "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                image = R.drawable.ic_tv_shows_supergirl,
                genre = "Drama, Sci-Fi & Fantasy, Action & Adventure",
                releaseYear = "2015",
                length = "42m",
                rating = "7.2/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "Supernatural",
                desription = "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                image = R.drawable.ic_tv_shows_supernatural,
                genre = "Drama, Mystery, Sci-Fi & Fantasy",
                releaseYear = "2005",
                length = "45m",
                rating = "8.2/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "The Simpsons",
                desription = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                image = R.drawable.ic_tv_shows_the_simpson,
                genre = "Family, Animation, Comedy",
                releaseYear = "1989",
                length = "22m",
                rating = "7.8/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "The Umbrella Academy",
                desription = "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                image = R.drawable.ic_tv_shows_the_umbrella,
                genre = "Action & Adventure, Sci-Fi & Fantasy, Drama",
                releaseYear = "2019",
                length = "55m",
                rating = "8.7/10"
            )
        )
        tvShows.add(
            TvShow(
                title = "The Walking Dead",
                desription = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                image = R.drawable.ic_tv_shows_the_walking_dead,
                genre = "Action & Adventure, Drama, Sci-Fi & Fantasy",
                releaseYear = "2010",
                length = "42m",
                rating = "8.0/10"
            )
        )
        return tvShows
    }
}