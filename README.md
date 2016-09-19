# PDFExtract

##Requirements
You will require the following conditions to run the program:
- JVM installed on the machine
- CLI tool to execute the jar

##For End Users
The built standalone jar file is able to extract bibliography items from a specified PDF document or a specified list. You can perform this task by typing the following command into a CLI program:

    java -jar [name].jar [input files] [output folder]

where you replace
- name with the name of the standalone jar file
- input files with the paths to the list of files you wish to extract the bibliography from, each separated with a space. Such as "C:/users/file1.pdf C:/users/file2.pdf"
- output folder with the path to the directory you wish to store the output

It will store the output JSON file with the name of the corresponding pdf.

##For Developers
The application has been built with Java. The package strucutre and its classes are explained below.

###Package - main
Contains the classes which are responsible for the extraction of the metadata

**Main class:**
- Entry point to program
- Does type checking
- Parses input parameters 
- Provides help if wrong parameters are provided

**MetadataStorer class:**
- POJO to store all the extracted information

**MyPDFParser class:**
- Extends swingworker to run on own thread
- Responsible for extracting the metadata information
- Retreives output and prints it into the corresponding file

**OutputPrinter class:**
- Repsonsible for printing metadataStorer into specified outputstream
- Prints using JSON or just displays the fields in metadata

