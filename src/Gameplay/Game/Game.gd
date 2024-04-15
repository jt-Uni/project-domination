extends Node2D

class_name Game

@onready var player_scene = preload("res://src/Gameplay/Player/Player.tscn")
@onready var players_queue: PlayersQueue = get_node("/root/Game/PlayersQueue")
@onready var quit_menu = get_node("hud/QuitGame")

func _ready():
	initialize_game()
	
func initialize_game():
	spawn_players()
	set_initial_troops()
	setup_hud()
	setup_game()
	
func spawn_players():
	var current_players = GamePlay.number_of_players
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

func setup_hud():
	quit_menu.hide()

func setup_game():
	GamePlay.game = self
	
func quit():
	quit_menu.show()
