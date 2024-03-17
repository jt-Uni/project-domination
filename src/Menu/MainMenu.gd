extends Control

@onready var test = preload("res://src/Gameplay/Game/Game.tscn")

func _on_play_button_pressed() -> void:
	#var play_scene = preload("res://src/Gameplay/Game/Game.tscn")
	#var play_instance = play_scene.instantiate() # This creates an instance of the node
	#get_tree().root.add_child(play_instance) # Now you're adding a Node, not a PackedScene
	#queue_free()
	
	var this_scene = test.instantiate()
	get_tree().root.add_child(this_scene)
	queue_free()

func _on_options_button_pressed():
	pass

func _on_quit_button_pressed():
	get_tree().quit()
