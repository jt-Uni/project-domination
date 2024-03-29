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

# Called when the node enters the scene tree for the first time.
func _ready():
	setup()
	
func setup():
	set_active(false)
	setup_reinforcements()
	
func set_active(value):
	is_active = value
	set_process_input(value)
	
func setup_reinforcements():
	reinforcement = randi() % 10 + 3

func set_initial_troops(amount, net_call=false):
	initial_troops = amount
	if net_call:
		return
