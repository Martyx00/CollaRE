import os, json

doc = Document.getCurrentDocument()

if ".collare_projects" in doc.getDatabaseFilePath():
    for seg_index in range(0,doc.getSegmentCount()):
        seg = doc.getSegment(seg_index)
        with open(os.path.join(os.path.dirname(doc.getDatabaseFilePath()),"changes.json"),"r") as changes_file:
            changes = json.load(changes_file)
            for comment in changes["comments"]:
                if seg.getCommentAtAddress(int(comment,10)):
                    if seg.getCommentAtAddress(int(comment,10)) in changes["comments"][comment]:
                        seg.setCommentAtAddress(int(comment,10),changes["comments"][comment])
                    else:
                        current_comment = seg.getCommentAtAddress(int(comment,10))
                        if current_comment:
                            seg.setCommentAtAddress(int(comment,10),current_comment + "; " +changes["comments"][comment])
                        else:
                            seg.setCommentAtAddress(int(comment,10),changes["comments"][comment])
                else:
                    seg.setCommentAtAddress(int(comment,10),changes["comments"][comment])

            for function in changes["function_names"]:
                if not seg.getProcedureAtAddress(int(function,10)):
                    seg.markAsProcedure(int(function,10))
                seg.setNameAtAddress(int(function,10),changes["function_names"][function]["name"])
    doc.message("Import completed!",["Ok"])
else:
    doc.message("This is not a CollaRE project!",["Ok"])
    pass