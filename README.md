
# CollaRE

## Intorduction

CollareRE is a tool for collaborative reverse engineering that aims to allow teams that do need to use more then one tool during a project to collaborate without the need to share the files on a seprate locations. It also contains a very simple user management and as such can be used for a multi-project servers where different teams work on different projects.
The [back-end](TODO) of the tool is a simple `Flask app` with `nginx` in front of it running in Docker that works with files and JSON based manifests that hold the relevant data. The front-end is a PyQT based GUI tool with a simple interface that allos managing the projects and working with the binary files and their corresponding reverse engineering databases. As of now the tool supports `Binary Ninja`, `Cutter (Rizin)`, `Ghidra`, `Hopper Dissassembler`, `IDA` and `JEB`. The implementation is abstracted from the inner workings of these tools as much as possible to avoid issues with any API changes and thus does not integrate directly into those tools in form of a plugin (might change in the future). The work is based purely on managing the files produced by these tools (literrally just based on the well known extensiions) and a simple SVN style `check-out` and `check-in` operations.

## Supported Tools

### Cutter (Rizin)

When saving Cutter (rizin) projects you have to append `.rzdb` manually.

### Binary Ninja


### Hopper Disassembler


### JEB


### IDA Pro


### Ghidra


## Disclaimer

I am not a good developer and I am even worse UI designer.