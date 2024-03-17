extends Area2D



func _on_mouse_entered():
	modulate = Color(0, 0, 1)


func _on_mouse_exited():
	modulate = Color(1, 1, 1)
