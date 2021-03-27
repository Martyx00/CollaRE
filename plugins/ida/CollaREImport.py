from idautils import *
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
    set_cmt(ea,"",False)
    set_cmt(ea,"",True)
    set_func_cmt(ea,"",False)
    set_func_cmt(ea,"",True)
    return comment


if ".collare_projects" in ida_nalt.get_input_file_path():
    with open(os.path.join(os.path.dirname(ida_nalt.get_input_file_path()),"changes.json"),"r") as changes_file:
        changes = json.load(changes_file)
        for function in changes["function_names"]:
            # Set function names
            idaapi.set_name(int(function),str(changes["function_names"][function]["name"]),idaapi.SN_FORCE)
        for comment in changes["comments"]:
            currentComment = get_comment(int(comment,10))
            if currentComment:
                if currentComment in changes["comments"][comment]:
                    set_cmt(int(comment,10),changes["comments"][comment],False)
                elif changes["comments"][comment] in currentComment:
                    set_cmt(int(comment,10),currentComment::,False)
                else:
                    set_cmt(int(comment,10),currentComment + "; " + changes["comments"][comment],False)
            else:
                set_cmt(int(comment,10),changes["comments"][comment],False)
    print("[*] Import completed!")
else:
    print("[!] This is not a CollaRE project!")
