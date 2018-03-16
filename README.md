# simpleDataCiteGenerator
A simple and very straightforward tool to create DataCite metadata format files.

* Status: alpha version
* Supported version of DataCite Metadata format: 4.1

## Description

This tool implements a simple API to create XML-based  DataCite Metadata record 
files from shallow data structures (for examples, CSV files, Openrefine output files 
or excel files.

Verification of most DataCite constraints are supported; however, the tool suppots text warnings in the current stage.

## Usage
The tool is configured by command line arguments
```
java DataCiteGenerator -i <inputfile> -o <outputfile> -p <parser name>
```
  
Currently, only "HosAggregator" is a valid option for the parser name. Feel free to create your own parsers.
