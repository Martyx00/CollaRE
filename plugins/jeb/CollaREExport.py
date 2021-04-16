#?description=Open and process a file into a JEB project.
#?shortcut=
from com.pnfsoftware.jeb.client.api import IScript, IconType, ButtonGroupType
from com.pnfsoftware.jeb.core.units import INativeCodeUnit
import os,json

"""
Sample script for JEB Decompiler.
"""
class CollaREExport(IScript):
    def run(self, ctx):
        prj = ctx.getMainProject()
        assert prj, 'Need a project'

        unit = prj.findUnit(INativeCodeUnit)
        assert unit, 'Need a native code unit'

        if not ".collare_projects" in prj.getName():
            changes = {"function_names":{},"comments":{}}
            for function in unit.getMethods():
                if function.getMemoryAddress():
                    address = int(function.getMemoryAddress())
                    name = function.getName()
                    if hex(address)[2:].upper() not in name:
                        changes["function_names"][address] = {"name":name,"end":0}
            comments = unit.getCommentManager().getComments()
            for comment in comments:
                comment_offset = int(comment.split("+")[-1][:-1],16)
                comment_function = '+'.join(comment.split("+")[:-1])
                comment_address = int(unit.getMethod(comment_function).getMemoryAddress()) + comment_offset
                changes["comments"][comment_address] = comments[comment].formatRaw()
            with open(os.path.join(os.path.dirname(prj.getName()),"changes.json"),"w") as changes_file:
                json.dump(changes,changes_file)
            ctx.displayMessageBox("CollaRE","Export Completed!",IconType.INFORMATION,ButtonGroupType.OK)
        else:
            ctx.displayMessageBox("CollaRE","Not a CollaRE project!",IconType.WARNING,ButtonGroupType.OK)