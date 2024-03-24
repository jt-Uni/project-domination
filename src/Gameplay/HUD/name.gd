extends Control

class_name test

@onready var name_label = $Name
@onready var state_label = $State

signal go_pressed

func set_player_name(player_name):
	name_label.text = player_name

func set_player_state(state):
	state_label.text = state
