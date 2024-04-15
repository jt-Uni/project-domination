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
	"alaska": ["northwest-territory", "alberta", "nothern-united-states"],
	"northwest-territory": ["alaska", "alberta", "ontario", "greenland"],
	"greenland": ["northwest-territory", "eastern-canad"],
	"alberta": ["alaska", "northwest-territory", "ontario", "nothern-united-states"],
	"ontario": ["northwest-territory", "alberta", "nothern-united-states", "eastern-canada"],
	"eastern-canada": ["greenland", "ontario", "nothern-united-states", "eastern-united-states"],
	"northern-united-states": ["alaska", "alberta", "ontario", "eastern-canada", "western-united-states", "eastern-united-states"],
	"western-united-states": ["northern-united-states", "eastern-united-states", "central-america"],
	"eastern-united-states": ["northern-united-states", "western-united-states", "central-america"],
	"central-america": ["western-united-states", "eastern-united-states", "venuzuela"],

	"venuzuela": ["central-america", "peru", "bolivia"],
	"peru": ["venuzuela", "peru", "brazil", "argentina"],
	"bolivia": ["venuzuela", "peru", "brazil"],
	"brazil": ["bolivia", "peru", "argentina"],
	"argentina": ["peru", "brazil"],
}

var bordering_countries_nodes = {
	"alaska": ["northwest-territory", "alberta", "nothern-united-states"],
	"northwest-territory": ["alaska", "alberta", "ontario", "greenland"],
	"greenland": ["northwest-territory", "eastern-canad"],
	"alberta": ["alaska", "northwest-territory", "ontario", "nothern-united-states"],
	"ontario": ["northwest-territory", "alberta", "nothern-united-states", "eastern-canada"],
	"eastern-canada": ["greenland", "ontario", "nothern-united-states", "eastern-united-states"],
	"northern-united-states": ["alaska", "alberta", "ontario", "eastern-canada", "western-united-states", "eastern-united-states"],
	"western-united-states": ["northern-united-states", "eastern-united-states", "central-america"],
	"eastern-united-states": ["northern-united-states", "western-united-states", "central-america"],
	"central-america": ["western-united-states", "eastern-united-states", "venuzuela"],

	"venuzuela": ["central-america", "peru", "bolivia"],
	"peru": ["venuzuela", "peru", "brazil", "argentina"],
	"bolivia": ["venuzuela", "peru", "brazil"],
	"brazil": ["bolivia", "peru", "argentina"],
	"argentina": ["peru", "brazil"],
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
