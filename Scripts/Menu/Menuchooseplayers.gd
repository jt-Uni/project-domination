extends Node

@onready var load_scene = load("res://Scenes/Gameplay.tscn")

# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass


func _on_back_button_pressed():
	var menuplayerscreen = load("res://Scenes/Menu/Main.tscn").instantiate()
	#var scene = menuplayerscreen.instantiate()
	get_tree().root.add_child(menuplayerscreen)
	queue_free()


func _on_one_player_button_pressed():
	var this_scene = load_scene.instantiate()
	get_tree().root.add_child(this_scene)
	queue_free()
