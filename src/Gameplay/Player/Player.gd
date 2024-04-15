extends Node2D

class_name Player

var is_active = false
var reinforcement = 0
var countries_occupied = 0
var countries = []
var countries_occupied_in_continents = {
	"NorthAmerica": 0,
	"SouthAmerica": 0,
	"Europe": 0,
	"Africa": 0,
	"Asia": 0,
	"Australia": 0
}
var first_turn = true
var initial_troops = 9
var eliminated = false

@onready var hud: Current_Player_Hud = find_child("Current_Player_Hud")
@onready var player_state = load("res://src/Gameplay/StateMachine/PlayerStates/PlacementState.gd").new()

# Called when the node enters the scene tree for the first time.
func _ready():
	setup()
	
func setup():
	set_active(false)
	setup_reinforcements()
	setup_hud()
	setup_state()
	connect_signals()
	
func set_active(value):
	is_active = value
	
	if value:
		hud.show()
	else:
		hud.hide()
	
	set_process_input(value)
	
func setup_reinforcements():
	reinforcement = randi() % 10 + 3
	
func setup_hud():
	var player_data = GamePlay.players_data[name]
	hud.set_player_name(player_data.name, player_data.number)
	
func setup_state():
	player_state.enter(self)
	
func connect_signals():
	connect("turn_completed", Callable(self, "turn_complete"))
	
func set_initial_troops(amount, net_call=false):
	initial_troops = amount
	if net_call:
		return

func change_state(state, net_call=false):
	print("Player ", name, " state changed.")
	var previous_state = player_state
	previous_state.exit(self)
	player_state = state
	print("Previous state: ", previous_state.get_class())
	print("New state: ", player_state.get_class())
	previous_state.queue_free()
	player_state.enter(self)
	if net_call:
		return
	state = state.get_state_name()

func country_clicked(country: Country):
	var state = player_state.country_clicked(self, country)
	if state:
		change_state(state)
