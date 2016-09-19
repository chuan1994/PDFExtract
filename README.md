# PDFExtract

##Requirements
You will require the following conditions to run the program:
- JVM installed on the machine
- CLI tool to execute the jar
- There are dependencies for this project, which have been provided in the top level folder of this repository (Standalone tool has these packaged inside)

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

###Package - reportCheckers
Contains all the classes which implement the ReportExtractor interface. These are used to store the logic used to extract the PDF
 
**ReportChecker interface:**
- Implement this interface to create classes which hold logic for extracting metadata
- Contains methods to get title, subtitle, authors, degrees, disciplines, supervisors, publication date, abstract, publisher, UUID.
 
**UOA.UOAReportChecker class:**
- Implements ReportChecker interface
- Prototype report checker for UOA theses extraction, now replaced by UOAThesisExtractor

**UOA.UOAThesisExtractor:**
- Implements ReportChecker interface
- Use this class to extract metadata from theses submissions at UOA, especially ones disciplined in computer science

###Package - helperClasses
Contains the classes which assist in extracting the metadata information

**PageDimensionCalc class:**
- Responsible for calculating the most common page size in a PDF document
- Generalises sizes to standard formats such as A4, A3 etc. but saves as other if dimensions matches none

**PDFValidator class:**
- Reponsible for ensuring the validity of the PDF
- Uses PDFBox to create a pdDoc, which throws an exception if it is not a PDF

**FontGroup class:**
- Simple class to store the font based information
- Stores - text, font, font size, page number

**MyTextStripper**
- Extends PDFTextStripper from the Apache PDFBox Library
- Extracts font information at new lines to produce a list of FontGroups
- Provides a print() function which prints all the current blocks in the list





