from idautils import *
from idaapi import *
import idaapi
from idc import *
import ida_nalt
import os, json

def get_comment(ea):
    comment = ""
    repComment = get_cmt(ea, True)
    nonRepComment = get_cmt(ea, False)
    repFunComment = get_func_cmt(ea,True)
    nonRepFunComment = get_func_cmt(ea,False)
    if repComment:
        comment += repComment
    if nonRepComment:
        if comment:
            comment += "; "
        comment += nonRepComment
    if repFunComment:
        if comment:
            comment += "; "
        comment += repFunComment
    if nonRepFunComment:
        if comment:
            comment += "; "
        comment += nonRepFunComment
    return comment

def clear_comments(ea):
    set_cmt(ea,"",False)
    set_cmt(ea,"",True)
    set_func_cmt(ea,"",False)
    set_func_cmt(ea,"",True)
    

class CollaREExportAction(idaapi.action_handler_t):
    def __init__(self):
        idaapi.action_handler_t.__init__(self)
    def activate(self, ctx):
        print("[CollaRE] Exporting ...")
        if ".collare_projects" in ida_nalt.get_input_file_path():
            changes = {"function_names":{},"comments":{},"base": int(idaapi.get_imagebase())}
            for segea in Segments():
                for funcea in Functions(segea):
                    # Name
                    functionName = get_func_name(funcea)
                    if hex(funcea)[2:].upper() not in functionName:
                        changes["function_names"][int(funcea)] = {"name":functionName,"end":0}
                for ea in range(segea,get_segm_end(segea)):
                    comment = get_comment(ea)
                    if comment:
                        changes["comments"][ea] = comment 
                
                with open(os.path.join(os.path.dirname(ida_nalt.get_input_file_path()),"changes.json"),"w") as changes_file:
                    json.dump(changes,changes_file)

            print("[CollaRE] Export completed!")
            info("CollaRE Export completed!")
        else:
            print("[CollaRE] Export failed! Not a CollaRE project!")
            warning("This is not a CollaRE project!")
        return 1
    
    # This action is always available.
    def update(self, ctx):
        return idaapi.AST_ENABLE_ALWAYS
    
class CollaREImportAction(idaapi.action_handler_t):
    def __init__(self):
        idaapi.action_handler_t.__init__(self)

    # Say hello when invoked.
    def activate(self, ctx):
        if ".collare_projects" in ida_nalt.get_input_file_path():
            with open(os.path.join(os.path.dirname(ida_nalt.get_input_file_path()),"changes.json"),"r") as changes_file:
                changes = json.load(changes_file)
                base = changes["base"]
                if base != int(idaapi.get_imagebase()):
                    base = int(idaapi.get_imagebase()) - base
                else:
                    base = 0
                for function in changes["function_names"]:
                    # Set function names
                    function_address = int(function) + base
                    idaapi.set_name(function_address,str(changes["function_names"][function]["name"]),idaapi.SN_FORCE)
                for comment in changes["comments"]:
                    comment_address = int(comment,10) + base
                    currentComment = get_comment(comment_address)
                    clear_comments(comment_address)
                    if currentComment:
                        if currentComment in changes["comments"][comment]:
                            set_cmt(comment_address,changes["comments"][comment],False)
                        elif changes["comments"][comment] in currentComment:
                            set_cmt(comment_address,currentComment,False)
                        else:
                            set_cmt(comment_address,currentComment + "; " + changes["comments"][comment],False)
                    else:
                        set_cmt(comment_address,changes["comments"][comment],False)
            print("[*] Import completed!")
            info("CollaRE Import completed!")
        else:
            print("[!] This is not a CollaRE project!")
            warning("This is not a CollaRE project!")
        return 1
        
    # This action is always available.
    def update(self, ctx):
        return idaapi.AST_ENABLE_ALWAYS


class collare_t(idaapi.plugin_t):
    comment = "CollaRE"
    help = "Use this plugin to share function names and comments across differrent tool within CollaRE workspace."
    wanted_name = "CollaRE"
    wanted_hotkey = ""
    flags = idaapi.PLUGIN_KEEP

    def init(self):
        # 2) Describe the action
        export_desc = idaapi.action_desc_t(
            'collare:export',   # The action name. This acts like an ID and must be unique
            'CollaRE Export',  # The action text.
            CollaREExportAction(),   # The action handler.
            '',      # Optional: the action shortcut
            'Export function names and comments for CollaRE projects.'  # Optional: the action tooltip (available in menus/toolbar)
            )           # Optional: the action icon (shows when in menus/toolbars)
        # 3) Register the action
        idaapi.register_action(export_desc)
        idaapi.attach_action_to_menu("File/Save as...", "collare:export", idaapi.SETMENU_APP)
        import_desc = idaapi.action_desc_t(
            'collare:import',   # The action name. This acts like an ID and must be unique
            'CollaRE Import',  # The action text.
            CollaREImportAction(),   # The action handler.
            '',      # Optional: the action shortcut
            'Import function names and comments for CollaRE projects.'  # Optional: the action tooltip (available in menus/toolbar)
            )           # Optional: the action icon (shows when in menus/toolbars)
        # 3) Register the action
        idaapi.register_action(import_desc)
        idaapi.attach_action_to_menu("File/Save as...", "collare:import", idaapi.SETMENU_APP)
        #idaapi.attach_action_to_menu("Edit/Plugins/CollaRE Import", self.collare_import(), 0)

    def run(self):
        pass

    def term(self):
        pass

def PLUGIN_ENTRY():
    return collare_t()