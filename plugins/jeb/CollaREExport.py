#?description=Open and process a file into a JEB project.
#?shortcut=
from com.pnfsoftware.jeb.client.api import IScript
from com.pnfsoftware.jeb.core.units import INativeCodeUnit
"""
Sample script for JEB Decompiler.
"""
class CollaREExport(IScript):
    def run(self, ctx):
        prj = ctx.getMainProject()
        assert prj, 'Need a project'

        unit = prj.findUnit(INativeCodeUnit)
        assert unit, 'Need a naive code unit'

        print(ctx.getProgramDirectory())
        for function in unit.getMethods():
            if function.getMemoryAddress():
                pass
                # Memory
                #print(function.getMemoryAddress())
                # Name
                #print(function.getName())
        comment_manager = unit.getCommentManager()