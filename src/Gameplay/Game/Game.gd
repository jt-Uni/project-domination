extends Node2D

class_name Game

@onready var player_scene = preload("res://src/Gameplay/Player/Player.tscn")
@onready var players_queue: PlayersQueue = get_node("/root/Game/PlayersQueue")
@onready var quit_menu = get_node("hud/QuitGame")

var active_player: Player = null
var active_player_index = -1
var occupied_countries = 0
var total_countries = 42
var number_of_players_placed_all_troops = 0
var auto_place = false

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
	
func active_player_changed(p_active_index, p_active_player: Player):
	active_player = p_active_player
	active_player_index = p_active_index
