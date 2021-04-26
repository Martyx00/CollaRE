# This script is used to import data for the use in CollaRE projects.
#@author Martin Petran 
#@category Collaboration
#@keybinding 
#@menupath Tools.CollaRE.Import
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

def clear_comments(address):
    setEOLComment(address,"")
    setPreComment(address,"")
    setPlateComment(address,"")
    setPostComment(address,"")


project_dir = getProjectRootFolder().getProjectLocator().getLocation()
if ".collare_projects" in project_dir:
    with open(os.path.join(project_dir,"changes.json"),"r") as changes_file:
        changes = json.load(changes_file)
    base = changes["base"]
    if base != int(currentProgram.getImageBase().getOffset()):
        base = int(currentProgram.getImageBase().getOffset()) - base
    else:
        base = 0
    for function_address in changes["function_names"]:
        function = getFunctionAt(toAddr(int(function_address) + base))
        if function:
            function.setName(changes["function_names"][function_address]["name"],ghidra.program.model.symbol.SourceType.USER_DEFINED)
    for comment in changes["comments"]:
        comment_address = toAddr(int(comment,10) + base)
        current_comment = get_comments(comment_address)
        clear_comments(comment_address)
        if current_comment:
            if current_comment in changes["comments"][comment]:
                setPreComment(comment_address,changes["comments"][comment])
            elif changes["comments"][comment] in current_comment:
                setPreComment(comment_address,current_comment)
            else:
                setPreComment(comment_address,current_comment + "; " + changes["comments"][comment])
        else:
            setPreComment(comment_address,changes["comments"][comment])

    popup("[*] Import successful!")
else:
    popup("[!] This is not CollaRE project!")