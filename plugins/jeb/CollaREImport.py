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
                for function in unit.getMethods():
                    if function.getMemoryAddress() and str(int(function.getMemoryAddress())) in changes["function_names"]:
                        function.setName(changes["function_names"][str(int(function.getMemoryAddress()))]["name"])
                comment_manager = unit.getCommentManager()
                for comment in changes["comments"]:
                    if comment_manager.getComment(hex(int(comment,10))):
                        currentComment = comment_manager.getComment(hex(int(comment,10))).formatRaw()
                    else:
                        currentComment = ""
                    if currentComment:
                        if currentComment in changes["comments"][comment]:
                            comment_manager.setComment(hex(int(comment,10)),Comment(changes["comments"][comment]),True)
                        elif changes["comments"][comment] in currentComment:
                            comment_manager.setComment(hex(int(comment,10)),Comment(currentComment),True)
                        else:
                            comment_manager.setComment(hex(int(comment,10)),Comment(currentComment + "; " + changes["comments"][comment]),True)
                    else:
                        comment_manager.setComment(hex(int(comment,10)),Comment(changes["comments"][comment]),True)
            ctx.displayMessageBox("CollaRE","Import Completed!",IconType.INFORMATION,ButtonGroupType.OK)
        else:
            ctx.displayMessageBox("CollaRE","Not a CollaRE project!",IconType.WARNING,ButtonGroupType.OK)