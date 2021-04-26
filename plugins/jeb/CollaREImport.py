#?description=Open and process a file into a JEB project.
#?shortcut=
from com.pnfsoftware.jeb.client.api import IScript, IconType, ButtonGroupType
from com.pnfsoftware.jeb.core.units import INativeCodeUnit
from com.pnfsoftware.jeb.core.units.impl import Comment

import os,json
"""
Sample script for JEB Decompiler.
"""
class CollaREImport(IScript):
    def run(self, ctx):
        prj = ctx.getMainProject()
        assert prj, 'Need a project'

        unit = prj.findUnit(INativeCodeUnit)
        assert unit, 'Need a native code unit'

        if ".collare_projects" in prj.getName():
            with open(os.path.join(os.path.dirname(prj.getName()),"changes.json"),"r") as changes_file:
                changes = json.load(changes_file)
                base = changes["base"]
                if base != int(unit.getVirtualImageBase()):
                    base = int(unit.getVirtualImageBase()) - base
                else:
                    base = 0
                for function in unit.getMethods():
                    if function.getMemoryAddress():
                        function_address = int(function.getMemoryAddress()) - base
                        if function.getMemoryAddress() and str(function_address) in changes["function_names"]:
                            function.setName(changes["function_names"][str(function_address)]["name"])
                comment_manager = unit.getCommentManager()
                for comment in changes["comments"]:
                    comment_address = int(comment,10) + base
                    print(comment_address)
                    print(changes["comments"][comment])
                    if comment_manager.getComment(hex(comment_address)):
                        currentComment = comment_manager.getComment(hex(comment_address)).formatRaw()
                    else:
                        currentComment = ""
                    if currentComment:
                        if currentComment in changes["comments"][comment]:
                            comment_manager.setComment(hex(comment_address),Comment(changes["comments"][comment]),True)
                        elif changes["comments"][comment] in currentComment:
                            comment_manager.setComment(hex(comment_address),Comment(currentComment),True)
                        else:
                            comment_manager.setComment(hex(comment_address),Comment(currentComment + "; " + changes["comments"][comment]),True)
                    else:
                        comment_manager.setComment(hex(comment_address),Comment(changes["comments"][comment]),True)
            ctx.displayMessageBox("CollaRE","Import Completed!",IconType.INFORMATION,ButtonGroupType.OK)
        else:
            ctx.displayMessageBox("CollaRE","Not a CollaRE project!",IconType.WARNING,ButtonGroupType.OK)