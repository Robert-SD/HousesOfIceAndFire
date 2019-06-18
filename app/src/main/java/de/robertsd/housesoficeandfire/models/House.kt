package de.robertsd.housesoficeandfire.models

data class House(
    val ancestralWeapons: List<String>,
    val cadetBranches: List<Any>,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val name: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val swornMembers: List<Any>,
    val titles: List<String>,
    val url: String,
    val words: String
)