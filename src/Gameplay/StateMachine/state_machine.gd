extends Node

class_name StateMachine

func enter(_player):
	pass
	#print("New state: ", get_class())

func handle_input(_player, _input: InputEvent):
	return null

func update(_player):
	pass

func exit(_player):
	pass
	#print("Previous state: ", get_class().)
