
# CollaRE

![CollaRE](./collare/icons/collare-full-white.png)


## Intorduction

CollareRE is a tool for collaborative reverse engineering that aims to allow teams that do need to use more then one tool during a project to collaborate without the need to share the files on a separate locations. It also contains a very simple user management and as such can be used for a multi-project servers where different teams work on different projects.
The [back-end](https://github.com/Martyx00/CollaREServer) of the tool is a simple `Flask app` with `nginx` in front of it running in Docker that works with files and JSON based manifests that hold the relevant data. The front-end is a PyQT based GUI tool with a simple interface that allows managing the projects and working with the binary files and their corresponding reverse engineering databases. As of now the tool supports `Binary Ninja`, `Cutter (Rizin)`, `Ghidra`, `Hopper Dissassembler`, `IDA` and `JEB`. The implementation is abstracted from the inner workings of these tools as much as possible to avoid issues with any API changes and thus does not integrate directly into those tools in form of a plugin (might change in the future). The work is based purely on managing the files produced by these tools (literally just based on the well known file extensions) and a simple SVN style `check-out` and `check-in` operations.

## Supported Tools

### Cutter (Rizin)

To enable support for this tool add a file `Cutter` to your path (when you open `cmd`/`terminal` writing `Cutter` should start the application). 
When saving Cutter (rizin) projects you have to manually append `.rzdb`. Do not remove the extension that the file already has (`exe` or `so` for example).
TODO https://github.com/rizinorg/cutter/pull/2606

### Binary Ninja

To enable support for this tool add a file `binaryninja` to your path (when you open `cmd`/`terminal` writing `binaryninja` should start the application).
Binary Ninja is removing file extensions by default, however the tool accounts for this so there is no need to put the original file extension back manually. Saving the projects as is in a default path is enough to be able to successfully push local `bndb` database.

### Hopper Disassembler

To enable support for this tool add a file `Hopper` to your path (when you open `cmd`/`terminal` writing `Hopper` should start the application).
Hopper is removing file extensions by default, however the tool accounts for this so there is no need to put the original file extension back manually. Saving the projects simply with `Ctrl+S` is enough to be able to successfully push local `hop` database.

### JEB

To enable support for this tool add a file `jeb` to your path (when you open `cmd`/`terminal` writing `jeb` should start the application).

### IDA Pro

To enable support for this tool add a file `ida64` to your path (when you open `cmd`/`terminal` writing `ida64` should start the application).

### Ghidra

To enable support for this tool add a file `ghidraRun` to your path (when you open `cmd`/`terminal` writing `ghidraRun` should start the application).
The process of initializing the database with Ghidra is a bit more complicated as there is no way that Ghidra will process file [without creating a project](https://github.com/NationalSecurityAgency/ghidra/issues/629). So to be able to push the Ghidra database (referred to as `ghdb`) you will be prompted to create a project manually and then specify the path to the `gpr` file (sorry for that).

## Usage

After deploying the server side as mentioned in its own readme file, it is necessary to distribute the used certificate file to all users of the application as well as use the default `admin` account with `admin` password to create other user accounts (don't forget to change password of `admin` user) via the `Admin` tab. When the users are configured anyone can create their own projects and start working with the tool itself.


## Disclaimer

I am not a good developer and I am even worse UI designer.