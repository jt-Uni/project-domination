extends Control

# replace with correct file directory
@onready var load_scene = load("")

func _on_play_button_pressed() -> void:
	
	#var this_scene = load_scene.instantiate()
	#get_tree().root.add_child(this_scene)
	#queue_free()
	
	var menuplayerscreen = load("res://Scenes/Menu/Menuchooseplayers.tscn").instantiate()
	get_tree().root.add_child(menuplayerscreen)
	queue_free()
	
func _on_options_button_pressed():
	pass # Replace with function body.

func _on_quit_button_pressed():
	get_tree().quit()
