extends Node2D

class_name Game

@onready var player_scene = preload("res://src/Gameplay/Player/Player.tscn")
@onready var players_queue: PlayersQueue = get_node("/root/Game/PlayersQueue")

var active_player: Player = null

func _ready():
	initialize_game()
	
func initialize_game():
	# Set up the map and any other initialization logic
	spawn_players()
	set_initial_troops()
	# setup_game()
	
func spawn_players():
	var current_players = GamePlay.number_of_players
	print("Spawning players: ", current_players)  # Debug log
	for i in range(current_players):
		var p = player_scene.instantiate()
		p.name = str(i)
		players_queue.add_child(p)

func set_initial_troops():
	var current_players = GamePlay.number_of_players
	var subtraction = 5 * (current_players - 1)
	var initial_troops = 45 - subtraction
	for player in players_queue.get_children():
		player.set_initial_troops(initial_troops)
