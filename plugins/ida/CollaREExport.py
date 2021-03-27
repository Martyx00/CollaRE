from idautils import *
from idaapi import *
from idc import *
import ida_nalt
import os, json


if ".collare_projects" in ida_nalt.get_input_file_path():
    changes = {"function_names":{},"comments":{}}
    for segea in Segments():
        for funcea in Functions(segea):
            # Name
            functionName = get_func_name(funcea)
            if hex(funcea)[2:].upper() not in functionName:
                changes["function_names"][int(funcea)] = {"name":functionName,"end":0}
            #print(functionName)
            # Address   
            #print(funcea)
        for ea in range(segea,get_segm_end(segea)):
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
            if comment:
                changes["comments"][ea] = comment 
        
        with open(os.path.join(os.path.dirname(ida_nalt.get_input_file_path()),"changes2.json"),"w") as changes_file:
            json.dump(changes,changes_file)

    print("[*] Export completed!")
else:
    print("[!] This is not a CollaRE project!")
