# simpleDataCiteGenerator
A simple and very straightforward tool to create DataCite metadata format files.

* Status: alpha version
* Recommended Java version: 8+
* Supported version of DataCite Metadata format: 4.1

## Description

This tool implements a simple API to create XML-based  DataCite Metadata record 
files from shallow data structures (for examples, CSV files, Openrefine output files 
or excel files.

Verification of most DataCite constraints are supported; however, the tool suppots text warnings in the current stage.

## Usage
The tool is configured by command line arguments. For a quick start, you can use the automatically generated build in /dist/

```
java -jar dist/simpleDataCiteGenerator -i <inputfile> -o <outputfilePrefix> -p <parser name>
```
  
Don't forget to add the additional library/libraries in dist/lib/* to your classpath.

Since the DataCite Format only allows for one record per file, Outputfiles are stored in bulk with numbered names, e.g. dataCiteRecord0019.xml

Currently, only "HosAggregator" is a valid option for the parser name. Feel free to create your own parsers.

## Upcomming features
* Finish the HOSAggregatorParser
* treat "source" field
* JSON export
* MySQL support

