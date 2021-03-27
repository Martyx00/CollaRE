#?description=Open and process a file into a JEB project.
#?shortcut=
from com.pnfsoftware.jeb.client.api import IScript
"""
Sample script for JEB Decompiler.
"""
class ProcessFile(IScript):
  def run(self, ctx):
    engctx = ctx.getEnginesContext()  
    if not engctx:  
      print('Back-end engines not initialized')  
      return  
  
    projects = engctx.getProjects()  
    if not projects:  
        print('There is no opened project')  
    return  