extends Node2D

class_name hud

@onready var name_label = $ColorRect/Info/Name
@onready var state_label = $ColorRect/Info/State

func set_player_name(player_name, player_number):
	name_label.text = player_name
	
	var color = Color.ALICE_BLUE
	
	color = GamePlay.players_data[str(player_number)].color
	name_label.set("custom_colors/font_color", color)

func set_player_state(state):
	state_label.text = state
