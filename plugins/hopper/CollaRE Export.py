import os, json, sys

doc = Document.getCurrentDocument()

# Check if this is Collare Project
if ".collare_projects" in doc.getDatabaseFilePath():
    # TODO warn about doing import first
    changes = {"function_names":{},"comments":{}}
    for seg_index in range(0,doc.getSegmentCount()):
        seg = doc.getSegment(seg_index)
        for procedure_index in range(0,seg.getProcedureCount()):
            address = seg.getProcedureAtIndex(procedure_index).getEntryPoint()
            function_name = seg.getNameAtAddress(address)
            if hex(int(address))[2:] not in function_name:
                changes["function_names"][int(address)] = {"name":function_name,"end":0}
        for addr in range(seg.getStartingAddress(),seg.getStartingAddress()+seg.getLength()):
            comment = seg.getCommentAtAddress(addr)
            if comment:
                changes["comments"][addr] = comment
    with open(os.path.join(os.path.dirname(doc.getDatabaseFilePath()),"changes.json"),"w") as changes_file:
        json.dump(changes,changes_file)
        
else:
    # TODO show popup that this is not Collare project
    doc.log("[!] This is not a CollaRE project!")
    pass