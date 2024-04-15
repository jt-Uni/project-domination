extends Node

var players = 1
var colors = {
	"1": Color.ORANGE,
	"2": Color.LIGHT_BLUE,
	"3": Color.YELLOW,
	"4": Color.LIGHT_SALMON,
	"5": Color.LIGHT_GREEN,
	"6": Color.LIGHT_CORAL
}
var total_countries_in_continents = {
	"NorthAmerica": 10,
	"SouthAmerica": 5,
	"Europe": 9,
	"Africa": 7,
	"Asia": 12,
	"Australia": 5
}

var bordering_countries = {
	"Alaska": ["Northwest-territory", "Alberta", "nothern-united-states"],
	"Northwest-territory": ["Alaska", "Alberta", "Ontario", "Greenland"],
	"Greenland": ["Northwest-territory", "Eastern-canada"],
	"Alberta": ["Alaska", "Northwest-territory", "Ontario", "nothern-united-states"],
	"Ontario": ["Northwest-territory", "Alberta", "nothern-united-states", "Eastern-canada"],
	"Eastern-canada": ["Greenland", "Ontario", "nothern-united-states", "Eastern-united-states"],
	"Northern-united-states": ["Alaska", "Alberta", "Ontario", "Eastern-canada", "Western-united-states", "Eastern-united-states"],
	"Western-united-states": ["Northern-united-states", "Eastern-united-states", "Central-america"],
	"Eastern-united-states": ["Northern-united-states", "Western-united-states", "Central-america"],
	"Central-america": ["Western-united-states", "Eastern-united-states", "venuzuela"],

	"Venuzuela": ["Central-america", "Peru", "Bolivia"],
	"Peru": ["venuzuela", "Peru", "Brazil", "Argentina"],
	"Bolivia": ["venuzuela", "Peru", "Brazil"],
	"Brazil": ["Bolivia", "Peru", "Argentina"],
	"Argentina": ["Peru", "Brazil"],
}

var bordering_countries_nodes = {
	"Alaska": ["Northwest-territory", "Alberta", "nothern-united-states"],
	"Northwest-territory": ["Alaska", "Alberta", "Ontario", "Greenland"],
	"Greenland": ["Northwest-territory", "Eastern-canada"],
	"Alberta": ["Alaska", "Northwest-territory", "Ontario", "nothern-united-states"],
	"Ontario": ["Northwest-territory", "Alberta", "nothern-united-states", "Eastern-canada"],
	"Eastern-canada": ["Greenland", "Ontario", "nothern-united-states", "Eastern-united-states"],
	"Northern-united-states": ["Alaska", "Alberta", "Ontario", "Eastern-canada", "Western-united-states", "Eastern-united-states"],
	"Western-united-states": ["Northern-united-states", "Eastern-united-states", "Central-america"],
	"Eastern-united-states": ["Northern-united-states", "Western-united-states", "Central-america"],
	"Central-america": ["Western-united-states", "Eastern-united-states", "venuzuela"],

	"Venuzuela": ["Central-america", "Peru", "Bolivia"],
	"Peru": ["venuzuela", "Peru", "Brazil", "Argentina"],
	"Bolivia": ["venuzuela", "Peru", "Brazil"],
	"Brazil": ["Bolivia", "Peru", "Argentina"],
	"Argentina": ["Peru", "Brazil"],
}

var game: Game
var number_of_players = 2
var players_data_template = {
	"0": {
		"number": "0",
		"name": "Player 1",
		"color": Color.ORANGE,
	},
	"1": {
		"number": "1",
		"name": "Player 2",
		"color": Color.LIGHT_BLUE,
	},
}
var players_data = players_data_template
