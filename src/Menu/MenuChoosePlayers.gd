extends Control

var mainMenuScene = preload("res://src/Menu/MainMenu.tscn")

func _on_back_button_pressed():
	var mainMenu = mainMenuScene.instance()
	get_tree().root.add_child(mainMenu)

func _on_two_player_button_pressed():
	print("Button pressed!")
	# Call a method in main.gd to start the game
	get_parent().get_node("main").initialize_game()
