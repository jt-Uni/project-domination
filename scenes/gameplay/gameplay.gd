extends Node2D

@onready var mapImage = $Sprite2D

func _ready():
	load_regions()
	
func _process(delta):
	pass

func load_regions():
	var image = mapImage.get_texture().get_image()
	var pixel_colour_dict = get_pixel_color_dict(image)
	var regions_dict = import_file("res://assets/map_data/regions.txt")
	
	for region_color in regions_dict:
		var region = load("res://scenes/gameplay/region_area.tscn").instantiate()
		region.region_name = regions_dict[region_color]
		get_node("Regions").add_child(region)
	
func get_pixel_color_dict(image):
	var pixel_color_dict = {}
	for y in range(image.get_width()):
		for x in range(image.get_width()):
			var pixel_color = "#" + str(image.get_pixel(int(x), int(y)).to_html(false))
			if pixel_color not in pixel_color_dict:
				pixel_color_dict[pixel_color] = []
			pixel_color_dict[pixel_color].append(Vector2(x,y))
	return pixel_color_dict
	
# impor and convert lists or dictonary
func import_file(filepath):
	var file = FileAccess.open(filepath, FileAccess.READ)
	if file != null:
		return JSON.parse_string(file.get_as_text().replace("-", " "))
	else: 
		print ("Failed to open file: ", filepath)
		return null
