# This script is used to export data for the use in CollaRE projects.
#@author Martin Petran 
#@category Collaboration
#@keybinding 
#@menupath Tools.CollaRE.Export
#@toolbar 

import os, json


def get_comments(address):
    comment = ""
    eolComment = getEOLComment(address)
    preComment = getPreComment(address)
    plateComment = getPlateComment(address)
    postComment = getPostComment(address)
    if eolComment:
        comment += eolComment
    if preComment:
        if comment:
            comment += "; "
        comment += preComment
    if plateComment:
        if comment:
            comment += "; "
        comment += plateComment
    if postComment:
        if comment:
            comment += "; "
        comment += postComment
    return comment


changes = {"function_names":{},"comments":{},"base": int(currentProgram.getImageBase().getOffset())}
project_dir = getProjectRootFolder().getProjectLocator().getLocation()
if ".collare_projects" in project_dir:
    # Functions
    current_function = getFirstFunction()
    while current_function != None:
        address = int(current_function.getEntryPoint().getOffset())
        if hex(address)[2:] not in current_function.getName():
            changes["function_names"][str(address)] = {"name":current_function.getName(),"end":0}
        current_function = getFunctionAfter(current_function)
    # Comments
    current_instruction = getFirstInstruction()
    while current_instruction != None:
        address = current_instruction.getAddress()
        # Only works for PRE comments as doing it for everything would be insane mess
        comment = get_comments(address)
        if comment:
            changes["comments"][str(address.getOffset())] = comment
        current_instruction = getInstructionAfter(current_instruction)
    with open(os.path.join(project_dir,"changes.json"),"w") as changes_file:
        json.dump(changes,changes_file)
    
    popup("[*] Export successful!")
else:
    popup("[!] This is not CollaRE project!")
