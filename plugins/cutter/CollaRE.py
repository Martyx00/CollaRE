import cutter, json, os
from PySide2.QtWidgets import QAction, QLabel, QPushButton, QMessageBox

class CollaREExport(cutter.CutterPlugin):
    name = "CollaRE Export"
    description = "This plugin allows sharing the data for CollaRE projects"
    version = "1.0"
    author = "Martin Petran"

    def setupPlugin(self):
        pass

    def setupInterface(self, main):
        self.main = main
        collare_import = QAction("CollaRE Import", main)
        collare_import.triggered.connect(self.collare_import)
        collare_export = QAction("CollaRE Export", main)
        collare_export.triggered.connect(self.collare_export)
        main.addMenuFileAction(collare_import)
        main.addMenuFileAction(collare_export)

    def terminate(self):
        pass

    def collare_export(self):
        project_path = cutter.cmd("e prj.file")
        if ".collare_projects" in project_path:
            base_addr = int(json.loads(cutter.cmd("iSj"))[0]["vaddr"])
            changes = {"function_names":{},"comments":{},"base":base_addr}
            functions = cutter.cmd("afij @@F")
            functions = functions.replace("]\n[",",")
            functions = json.loads(functions)
            for function in functions:
                if hex(function["offset"])[2:] not in function["name"]:
                    # Non-default function names
                    changes["function_names"][function["offset"]] = {"name":function["name"],"end":function["offset"]+function["size"]}

            comments = cutter.cmd("CCfj @@F")
            comments = comments.replace("[]\n","").replace("]\n[",",")
            comments = json.loads(comments)
            for comment in comments:
                changes["comments"][comment["offset"]] = comment["name"]
            with open(os.path.join(os.path.dirname(project_path),"changes.json"),"w") as changes_file:
                json.dump(changes,changes_file)
            QMessageBox.information(self.main, "CollaRE", "Export Done!", QMessageBox.Ok)
        else:
            QMessageBox.warning(self.main, "CollaRE", "Not a CollaRE project!", QMessageBox.Ok)

    def get_comment_at(self,address):
        return cutter.cmd(f"CC.{hex(address)}").rstrip()

    def set_comment_at(self,address,comment):
        escaped_at = "\\@"
        escaped_semi = "\\;"
        cutter.cmd(f'CCa {hex(address)} {comment.replace("@",escaped_at).replace(";",escaped_semi)}')

    def rename_function(self,address,new_name):
        cutter.cmd(f"s {hex(address)}; afn {new_name}")

    def collare_import(self):
        project_path = cutter.cmd("e prj.file")
        if ".collare_projects" in project_path:
            with open(os.path.join(os.path.dirname(project_path),"changes.json"),"r") as changes_file:
                changes = json.load(changes_file)
                base = changes["base"]
                if base != int(json.loads(cutter.cmd("iSj"))[0]["vaddr"]):
                    base = int(json.loads(cutter.cmd("iSj"))[0]["vaddr"]) - base
                else:
                    base = 0
                for comment in changes["comments"]:
                    comment_address = int(comment,10) + base
                    current_comment = self.get_comment_at(comment_address)
                    if current_comment:
                        if self.get_comment_at(comment_address) in changes["comments"][comment]:
                            self.set_comment_at(comment_address,changes["comments"][comment])
                        elif changes["comments"][comment] in current_comment:
                            pass
                        else:
                            self.set_comment_at(comment_address,current_comment + "; " + changes["comments"][comment])
                    else:
                        self.set_comment_at(comment_address,changes["comments"][comment])
                for function in changes["function_names"]:
                    self.rename_function(int(function,10) + base,changes["function_names"][function]["name"])
            QMessageBox.information(self.main, "CollaRE", "Import Done!", QMessageBox.Ok)
        else:
            QMessageBox.warning(self.main, "CollaRE", "Not a CollaRE project!", QMessageBox.Ok)



def create_cutter_plugin():
    return CollaREExport()

