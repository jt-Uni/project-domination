extends Node

var players = 0
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
	"Afghanistan": ["China", "India", "MiddleEast", "Ukraine", "Ural"],
}

var bordering_countries_nodes = {
	"Afghanistan": ["China", "India", "MiddleEast", "Ukraine", "Ural"],
}

var game: Game
var number_of_players: int = 2
var players_data_template = {
	"0": {
		"name": "Player 1",
		"color": Color.ORANGE,
	},
	"1": {
		"name": "Player 2",
		"color": Color.LIGHT_BLUE,
	},
	"2": {
		"name": "Player 3",
		"color": Color.YELLOW,
	},
	"3": {
		"name": "Player 4",
		"color": Color.LIGHT_SALMON,
	},
	"4": {
		"name": "Player 5",
		"color": Color.LIGHT_GREEN,
	},
	"5": {
		"name": "Player 6",
		"color": Color.LIGHT_CORAL,
	},
}
var players_data = players_data_template
