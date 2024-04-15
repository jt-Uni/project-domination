extends Control

@onready var play = preload("res://src/Gameplay/Game/Game.tscn")
@onready var player_select = $ChoosePlayerCount
@onready var first_page = $FirstPage

func _ready():
	player_select.hide()

func _on_play_button_pressed():
	var this_scene = play.instantiate()
	get_tree().root.add_child(this_scene)
	queue_free()
	
func _on_options_button_pressed():
	first_page.hide()
	player_select.show()

func _on_quit_button_pressed():
	get_tree().quit()

func _on_back_button_pressed():
	player_select.hide()
	first_page.show()
