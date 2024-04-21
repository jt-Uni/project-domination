extends Control

class_name Main

@onready var error_label = $Error
@onready var success_label = $Success

func _ready():
	setup()

func setup():
	#setup_music()
	setup_mode()

func setup_music():
	GamePlay.set_music_volume(GamePlay.main_menu_volume)

func setup_mode():
	GamePlay.online = false

func options_saved():
	setup_music()
