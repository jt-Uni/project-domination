extends Node2D

class_name Game

var player_scene = preload("res://src/Gameplay/Player/Player.tscn")

func _ready():
	initialize_game()
	
func initialize_game():
	# Set up the map and any other initialization logic
	spawn_players()
	# setup_game()
	
func spawn_players():
	var current_players = GamePlay.number_of_players
	var players_queue = get_node("PlayersQueue")
	
	for i in range(current_players):
		var p = player_scene.instantiate()
		p.name = str(i)
		players_queue.add_child(p)
