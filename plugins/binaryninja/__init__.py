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
		for comment in changes["comments"]:
			if not changes["comments"][comment] in bv.get_comment_at(int(comment,10)):
				current_comment = bv.get_comment_at(int(comment,10))
				for function in bv.get_functions_containing(int(comment,10)):
					if function.get_comment_at(int(comment,10)):
						if current_comment:
							current_comment += "; " + function.get_comment_at(int(comment,10))
						else:
							current_comment = function.get_comment_at(int(comment,10))
						if current_comment in changes["comments"][comment]:
							function.set_comment_at(int(comment,10),changes["comments"][comment])
						elif changes["comments"][comment] in current_comment:
							pass
						else:
							function.set_comment_at(int(comment,10),current_comment + "; "+ changes["comments"][comment])
					else:
						function.set_comment_at(int(comment,10),changes["comments"][comment])
		for function in changes["function_names"]:
			# TODO if the function does not exist, create it - needs to be tested properly
			if not bv.get_function_at(int(function,10)):
				bv.create_user_function(int(function,10))
			#if bv.get_function_at(int(function,10)).highest_address == changes["function_names"][function]["end"]:
			bv.get_function_at(int(function,10)).name = changes["function_names"][function]["name"]
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
	changes = {"function_names":{},"comments":{}}
	for function in bv.functions:
		changes["comments"].update(function.comments)
		# Avoid storing default names that contain function address
		if hex(function.start)[2:] not in function.name:
			# TODO replace "end" with basic block count????
			changes["function_names"][function.start] = {"name":function.name,"end":function.highest_address}
	with open(os.path.join(os.path.dirname(bv.file.filename),"changes.json"),"w") as changes_file:
		json.dump(changes,changes_file)
	show_message_box("Export Successful", "Comments and function names exported. Please check-in the DB file to push the changes to the server.", buttons=0, icon=0)



PluginCommand.register("CollaRE\\Import Changes", "Import Changes", import_changes)
PluginCommand.register("CollaRE\\Export Changes", "Export Changes", export_changes)
