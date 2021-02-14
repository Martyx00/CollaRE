#!/usr/bin/python3

from pathlib import Path
import os
from PyQt5 import QtCore, QtGui, QtWidgets
from PyQt5.QtWidgets import QApplication
import sys
import ui


# upon connection also save URL, cert path and username there in connection.json
collare_home = str(Path.home() / ".collare_projects")
connected = False


class CollaRE(QtWidgets.QMainWindow, ui.Ui_Dialog):
    def __init__(self, parent=None):
        super(CollaRE, self).__init__(parent)
        self.setupUi(self)

def main():
    app = QApplication(sys.argv)
    form = CollaRE()
    form.show()
    app.exec_()

if __name__ == '__main__':
    # Create projects directory if it does not exist
    if not os.path.isdir(collare_home):
        os.mkdir(collare_home)
    main()