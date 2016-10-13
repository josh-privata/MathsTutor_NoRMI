<h1 align="center">
  <a href="http://java.com/en"><img src="https://cloud.githubusercontent.com/assets/5771200/19331298/6f964780-9127-11e6-88bd-55ac19e1ad12.jpg" alt="Java" height="150"></a>
  <br>
  <br>
  Maths Tutor (Implementing Serialization)
  <br>
  <br>
</h1>
<h4 align="center">A simple java client/server program to understand implementing RMI</h4>

<p align="center">
  <a href=""><img src="https://img.shields.io/travis/feross/standard/master.svg" alt="Passing"></a>
  <a href="https://java.com/en/"><img src="https://img.shields.io/badge/Java-1.8.0__101-brightgreen.svg" alt="Java 1.8.0"></a>
  <a href="https://opensource.org/licenses/BSD-2-Clause"><img src="https://img.shields.io/badge/License-BSD-blue.svg" alt="BSD License"></a>
</p>
<br>

## Table of Contents
- [Synopsis](#synopsis)
- [Install](#install)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [License](#license)

## Synopsis
The problem put forward was to design and code our own basic implementation of RMI using 
Java TCP API Socket. Using TCP protocol and client/server model, you can implement a remote method
invocation framework. In this assignment, you are to implement such a remote method
invocation framework by using:

+ Java TCP API Socket and ServerSocket
+ Java multi-threading.

The MathsTutor allows the user to practice arithmetic operations as chosen by 
the user. The user can select one of addition, subtraction, multiplication, or 
division. 

On receiving the user’s choice from the client side, the server has 
to send randomly generated double digit numbers to the client. The MathsTutor 
has got method to create arithmetic problems using randomly generated double
digit numbers. 

On receiving the problem, the client has to display the
problem and read the user input which is the answer consisting of an integer 
and send it to the server. The server has to invoke the method to execute 
the required operation, check the result and send a reply informing whether 
the answer is correct or incorrect to the client. 

On completion of 10 problems, the server has to send a summary of results, and 
allow the user choose another arithmetic operation if desired. The user should 
be able to stop the service by entering a single character ‘Q’ or ‘q’.
## Install
First, make a directory to install the files to and change to that directory using :
```bash
$ mkdir mathstutor && cd mathstutor
```
Then all you need to do is clone the project from github into the directory by using :
```bash
$ git clone https://github.com/josh-privata/MathsTutor_NoRMI.git
```
## Usage
##### Note:  [Java Runtime](https://java.com/en/download/) is required to run the preceding commands.
Initially the program needs to be compiled. After you have copied the *.java files to a directory, run the command :
```bash
$ javac *.java
```
Then run the program in a terminal window using the command :
```bash
$ java Server
```
And in a second terminal window use the command : 
```bash
$ java Client
```
## Screenshots
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331600/faa83f94-9128-11e6-9f65-60fb5ad92d36.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331603/facbb8de-9128-11e6-9683-6022e94b3bd9.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331605/fad04048-9128-11e6-9527-5199decfa1c8.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331607/fad82010-9128-11e6-9271-2218bb70e861.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331606/fad50664-9128-11e6-95ee-7c02b2a9950a.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19331608/faf5338a-9128-11e6-8ddc-8927d35bd75d.png" alt="Screenshot"></p>
<p align="center"><img src="https://cloud.githubusercontent.com/assets/5771200/19332400/f1245cd6-912e-11e6-8e3b-92a2ede5f923.png" alt="Screenshot"></p>

## License
[BSD](LICENSE) Copyright (c) 2016 [Josh Cannons](http://joshcannons.com).
