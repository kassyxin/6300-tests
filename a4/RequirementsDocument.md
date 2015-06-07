# **Requirements Document -- Student Xin Wang

##1 User Requirements

###1.1 User Characteristics

This software is designed for college students who need to write assays and want to perfect their writing style. Students who have no technical experience in computers, and those who possess intermediate or proficient technical skills with computers are all intended users of this software. The students shall have experience using computers with at least one of the existing operating system environments. 

###1.2 System's functionality

I would like to run the software and check the average sentence length of my essay on computers with any operating system environment. I want to be able to tell the software where the raw text file of my essay is via a command line. 

I want the software to consider any word that contains three letters or above as a word by default. I also want some flexibility so that I can customize the minimum world length. Moreover, I want the software to take anything that is delimited by any of a space, sequence of spaces, and a tab as a word by default, and I can also specify other word delimiters. 

I want the software to take anything that ends in a period, a question mark, a semi-colon and an exclamation mark as a sentence. I also want to be able to configure whether other delimiters, such as a comma, should be considered as a sentence separator. 

If everything goes smoothly, I want the software to return the average sentence length rounded down to the nearest integer. Finally, I would like friendly and helpful messages when errors occur.  


###1.3 User Interfaces

The user will be able to provide a file path to the file they wish to be analyzed as a command line argument. 

The user will be able to specify which delimiters count as sentence separators, using the flag -d. If -d is not provided, the default delimiters used must include a period, a question mark, a semi-colon and an exclamation mark, but not a comma. 

The user will be able to specify a lower limit for word length, using the flag -l. If -l is not provided, the default minimum length must be 3 characters.

The user will be able to specify which delimiters count as word separators, using the flag -w. If -w is not provided, the default delimiters used must include a space, sequence of spaces, and a tab. 

In the end if no error occurs, the software will show the students the average number of words per sentence rounded down to the nearest integer.

##2 System Requirements
 
###2.1 Functional Requirements

2.1.1 The software must be written in Java and must not make use of any nonstandard Java libraries.   
2.1.2 The software must have a main method and should be executable from the command line using the Java command.
2.1.3 The software must be pre-compiled and testable on machines running Java 1.6 or 1.7.
2.1.4 The software must come with end user documentation. 
2.1.5 The software must take raw text file as input. The file path to the file is provided by the user via a command line argument.
2.1.6 The software must return friendly and helpful error messages to the user when anything goes wrong. For example, the software should display “File can not be found” when it can not locate the input text file provided by the user.
2.1.7 The software must include a space, sequence of spaces, and a tab as default word delimiters.
2.1.8 The software must allow users to customize word delimiters using flag -w.
2.1.9 The software must set minimum word length as 3 by default.
2.1.10 The software must allow users to customize minimum word length using flag -l.
2.1.11 The software must include a period, a question mark, a semi-colon and an exclamation mark as default sentence delimiters.
2.1.12 The software must allow users to customize sentence delimiters using flag -d.
2.1.13 The software must output the average number of words per sentence rounded down to the nearest integer.


###2.2 Non-Functional Requirements
2.2.1 The system should be able to accommodate more than 270 users. 
2.2.2 The program should be able to finish analyzing an essay in less than 10 minutes so that the users will not need to wait long for the results.