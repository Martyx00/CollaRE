# This script is used to import data for the use in CollaRE projects.
#@author Martin Petran 
#@category Collaboration
#@keybinding 
#@menupath Tools.CollaRE.Import
#@toolbar 

import os, json

project_dir = getProjectRootFolder().getProjectLocator().getLocation()
if ".collare_projects" in project_dir:
    with open(os.path.join(project_dir,"changes.json"),"r") as changes_file:
        changes = json.load(changes_file)
    for function_address in changes["function_names"]:
        function = getFunctionAt(toAddr(int(function_address)))
        function.setName(changes["function_names"][function_address]["name"],ghidra.program.model.symbol.SourceType.USER_DEFINED)
    for comment in changes["comments"]:
        address = toAddr(int(comment,10))
        if getPreComment(address):
            if getPreComment(address) in changes["comments"][comment]:
                setPreComment(address,changes["comments"][comment])
            elif changes["comments"][comment] in getPreComment(address):
                pass
            else:
                current_comment = getPreComment(address)
                if current_comment:
                    setPreComment(address,current_comment + "; " + changes["comments"][comment])
                else:
                    setPreComment(address,changes["comments"][comment])
        else:
            setPreComment(address,changes["comments"][comment])

    print("[*] Import successful!")
else:
    print("[!] This is not CollaRE project!")