import cutter, json
from PySide2.QtWidgets import QAction, QLabel, QPushButton, QMessageBox

class CollaREDockWidget(cutter.CutterDockWidget):
    def __init__(self, parent, action):
        super(CollaREDockWidget, self).__init__(parent, action)
        self.setObjectName("CollaREDockWidget")
        self.setWindowTitle("CollaRE")

        
        exportButton = QPushButton("Export",self)
        exportButton.setGeometry(10, 50, 100, 40)
        exportButton.clicked.connect(self.collare_export)

        importButton = QPushButton("Import",self)
        importButton.setGeometry(160, 50, 100, 40)
        importButton.clicked.connect(self.collare_import)

    def collare_export(self):
        changes = {"function_names":{},"comments":{}}
        QMessageBox.warning(self, "CollaRE", "Export", QMessageBox.Ok | QMessageBox.Cancel)
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
        print(str(changes))
        #print(functions[:14050])

    def collare_import(self):
        QMessageBox.information(self, "CollaRE", "Import", QMessageBox.Ok | QMessageBox.Cancel)
        #print(cutter.getOpenedFiles())
        #changes = json.load(changes_file)
        changes = {"function_names":{},"comments":{}}
        for comment in changes["comments"]:
            if cutter.getComment(int(comment,10)):
                if cutter.getComment(int(comment,10)) in changes["comments"][comment]:
                    cutter.setComment(int(comment,10),changes["comments"][comment])
                elif changes["comments"][comment] in cutter.getComment(int(comment,10)):
                    pass
                else:
                    current_comment = cutter.getComment(int(comment,10))
                    if current_comment:
                        cutter.setComment(int(comment,10),current_comment + "; " + changes["comments"][comment])
                    else:
                        cutter.setComment(int(comment,10),changes["comments"][comment])
            else:
                cutter.setComment(int(comment,10),changes["comments"][comment])

        for function in changes["function_names"]:
            cutter.renameFunction(int(function,10),changes["function_names"][function]["name"])

class CollaREExport(cutter.CutterPlugin):
    name = "CollaRE Export"
    description = "This plugin allows sharing the data for CollaRE projects"
    version = "1.0"
    author = "Martin Petran"

    def setupPlugin(self):
        
        pass

    def setupInterface(self, main):
        action = QAction("CollaRE", main)
        action.setCheckable(True)
        widget = CollaREDockWidget(main, action)
        main.addPluginDockWidget(widget, action)

    def terminate(self):
        pass

def create_cutter_plugin():
    return CollaREExport()

