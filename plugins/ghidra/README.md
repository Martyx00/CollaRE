# CollaRE Ghidra Plugin

This plugin allows importing and exporting project edited project data (function names and comments).

## Installation

Place both Import and Export scripts into the folder with Ghidra scripts or create new scripts in editor and copy & paste the code.

## Usage

Run the script from `Tools > CollaRE > Import/Export`. Make sure to always do Import before you export something to avoid data loss! Note that running this script will cause all comments to be changed to *PREComment* type.