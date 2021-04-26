import os, json

doc = Document.getCurrentDocument()

if ".collare_projects" in doc.getDatabaseFilePath():
    for seg_index in range(0,doc.getSegmentCount()):
        seg = doc.getSegment(seg_index)
        with open(os.path.join(os.path.dirname(doc.getDatabaseFilePath()),"changes.json"),"r") as changes_file:
            changes = json.load(changes_file)
            #int(Document.getCurrentDocument().getSegment(0).getFileOffset())
            base = changes["base"]
            if base != int(doc.getSegment(0).getFileOffset()):
                base = int(doc.getSegment(0).getFileOffset()) - base
            else:
                base = 0
            for comment in changes["comments"]:
                comment_address = int(comment,10) + base
                if seg.getCommentAtAddress(comment_address):
                    if seg.getCommentAtAddress(comment_address) in changes["comments"][comment]:
                        seg.setCommentAtAddress(comment_address,changes["comments"][comment])
                    elif changes["comments"][comment] in seg.getCommentAtAddress(comment_address):
                        pass
                    else:
                        current_comment = seg.getCommentAtAddress(comment_address)
                        if current_comment:
                            seg.setCommentAtAddress(comment_address,current_comment + "; " + changes["comments"][comment])
                        else:
                            seg.setCommentAtAddress(comment_address,changes["comments"][comment])
                else:
                    seg.setCommentAtAddress(comment_address,changes["comments"][comment])

            for function in changes["function_names"]:
                function_address = int(function,10) + base
                if not seg.getProcedureAtAddress(function_address):
                    seg.markAsProcedure(function_address)
                seg.setNameAtAddress(function_address,changes["function_names"][function]["name"])
    doc.message("Import completed!",["Ok"])
else:
    doc.message("This is not a CollaRE project!",["Ok"])
    pass