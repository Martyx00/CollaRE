from binaryninja import *
import os, json

imported = False

def import_changes(bv):
	# Check if we are working with CollaRE project
	if not ".collare_projects" in bv.file.filename:
		show_message_box("Not a CollaRE Project!", "CollaRE features are only available for files opened as CollaRE projects!", buttons=0, icon=2)
		return
	with open(os.path.join(os.path.dirname(bv.file.filename),"changes.json"),"r") as changes_file:
		changes = json.load(changes_file)
		base = changes["base"]
		if base != bv.start:
			base = bv.start - base
		else:
			base = 0
		for comment in changes["comments"]:
			comment_address = int(comment,10) + base
			if not changes["comments"][comment] in bv.get_comment_at(comment_address):
				current_comment = bv.get_comment_at(comment_address)
				for function in bv.get_functions_containing(comment_address):
					if function.get_comment_at(comment_address) and current_comment:
						current_comment += "; " + function.get_comment_at(comment_address)
					else:
						current_comment = function.get_comment_at(comment_address)
					
					if current_comment in changes["comments"][comment]:
						bv.set_comment_at(comment_address,"")
						function.set_comment_at(comment_address,"")
						function.set_comment_at(comment_address,changes["comments"][comment])
					elif changes["comments"][comment] in current_comment:
						pass
					else:
						function.set_comment_at(comment_address,current_comment + "; "+ changes["comments"][comment])
		for function in changes["function_names"]:
			function_address = int(function,10) + base
			if not bv.get_function_at(function_address):
				bv.create_user_function(function_address)
			if bv.get_function_at(function_address):
				bv.get_function_at(function_address).name = changes["function_names"][function]["name"]
	show_message_box("CollaRE Import", "Import successful!", buttons=0, icon=0)



def export_changes(bv):
	# Check if we are working with CollaRE project
	if not ".collare_projects" in bv.file.filename:
		show_message_box("Not a CollaRE Project!", "CollaRE features are only available for files opened as CollaRE projects!", buttons=0, icon=2)
		return
	# Warn about neccessity to import first
	result = show_message_box("Import not performed in this session", "It is strongly suggested to first perform an import before exporting any data. Would you like to continue?", buttons=1, icon=0)
	if result == 0:
		return
	changes = {"function_names":{},"comments":{},"base":bv.start}
	for function in bv.functions:
		changes["comments"].update(function.comments)
		# Avoid storing default names that contain function address
		if hex(function.start)[2:] not in function.name:
			changes["function_names"][function.start] = {"name":function.name,"end":function.highest_address}
	with open(os.path.join(os.path.dirname(bv.file.filename),"changes.json"),"w") as changes_file:
		json.dump(changes,changes_file)
	show_message_box("Export Successful", "Comments and function names exported. Please check-in the DB file to push the changes to the server.", buttons=0, icon=0)



PluginCommand.register("CollaRE\\Import Changes", "Import Changes", import_changes)
PluginCommand.register("CollaRE\\Export Changes", "Export Changes", export_changes)
