***********************************
*** LAB 2 - NewsFeed Portal  ***
***********************************

This is a sample project for LAB 2 in "Web Programming" 2012 course.

*******************************
***   Application modules   ***
*******************************

Application consists of two modules:

    - "lab02-newsfeed-core" : contains Java classes - domain model, services, Spring MVC classes
							: is packaged in a JAR file, which is later placed into Web module's folder "/WEB-INF/lib/"

    - "lab02-newsfeed-portal": Web module
                          : "\lab02-newsfeed-portal\src\main\webapp" contains Web application resources (jsp, css, images etc)


*******************************
***       Preparation       ***
*******************************

1) Create "newsfeed" database and user in MySQL using a script
\lab02-newsfeed\lab02-newsfeed-core\src\main\resources\init.sql

If database and user already exist (you have created it during the first lab),
then to avoid schema incompatibility better drop schema (delete all tables).
Otherwise you may experience persistence problems during execution!
Necessary tables will be created on application start up.

2) To modify projects in Eclipse execute "mvn eclipse:eclipse" respectively from
"lab02-newsfeed-core" and "lab02-newsfeed-portal".
In Eclipse delete main "lab02-newsfeed" root project (do not delete contents) and import sub-projects in Eclipse separately.

3) Run Java program lv.lu.newsfeed.UsersImportTool (you have to be familiar with this program from the first lab).
Program will import mock users data into the database.

4) Execute "mvn -Dmaven.test.skip=true install" from \lab02-newsfeed

*******************************
***       Packaging         ***
*******************************
To build a WAR file execute "mvn package -Dmaven.test.skip=true" from the root folder "lab02-newsfeed".
Application WAR file will be created in "\lab02-newsfeed\lab02-newsfeed-portal\target".
Deploy "lab02-newsfeed-portal.war" on application server (Jetty, Tomcat, JBoss or any other) and run application.
Application was tested on Jetty integrated with Maven.

*******************************
***  Running with Jetty     ***
*******************************

Jetty is a web server integrated with Maven, which can be simply launched from command line.
To rebuild application and start Jetty simply run \lab02-newsfeed\run_jetty.bat
To start with remote debugging enabled run \lab02-newsfeed\run_jetty_debug.bat

These scripts consist of two steps, which can be executed separately:

1) To package "lab02-newsfeed-core" in JAR file and install it into local Maven repository:
Go to \lab02-newsfeed\lab02-newsfeed-core\ and execute 
	mvn install
You have to perform this step each time when you change code in "lab02-newsfeed-core" and want these changes to appear in packaged web application.

2) To deploy lab02-newsfeed-portal.war and start Jetty (without re-packaging "lab02-newsfeed-core"):
Go to  \lab02-newsfeed\lab02-newsfeed-portal\ and execute
	mvn clean package jetty:run

*******************************
***  Launching application  ***
*******************************

Application can be accessed by opening the following URL in a browser: 
http://localhost:8080/lab02-newsfeed-portal/

For login use any user present in lv.lu.newsfeed.impl.load.user.MockUserDataLoader (but check that data is imported into database).
E.g. ben_armstrong/ben_armstrong.
Or you may create your own user directly in database.


*******************************
***  Selenium tests         ***
*******************************

There are two possibilities to execute prepared Selenium tests:
* using Selenium IDE
* integrating Selenium Core into a project

1) Install Selenium IDE (Firefox plugin), available at http://seleniumhq.org/download/
In Selenium IDE select: File > Open Test Suite... > \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\tests\newsfeed-suite\TestSuite.html
Play suite tests.

2) To be able to run Selenium tests directly in a browser you will need to download Selenium Core from http://release.seleniumhq.org/selenium-core/1.0/
Download selenium-core-1.0.1.zip and unzip it under \lab02-newsfeed\lab02-newsfeed-portal\src\main\webapp\selenium-core
When application is running on a server Selenium tests can be accessed by URL:
http://localhost:8080/lab02-newsfeed-portal/tests/
Precondition for tests - users data imported into database (UsersImportTool program executed).

**************************************
*** Integrating your Lab1 solution ***
**************************************

For sure you will need to update "lab02-newsfeed-core" content with a
certain part of your solution for the first lab - domain model classes that you have made persistent entities, services etc.
Do it, but be careful, this may require additional tuning.

******************************
***   Remote debugging     ***
******************************

To run Jetty with remote debugging mode enabled execute: run_jetty_debug.bat

For a tip on how to debug server-side code see
http://files-ante-lv.googlecode.com/svn/trunk/Training.WebProgramming.Masters2011/presentations/Lekcija07 - 03_Server-Side Debugging_2011_10_20..ppt

****************************
*** Problems & Questions ***
****************************

If you experience any problem or observe any strangeness or have any question,
please don't delay it, write e-mail or ask during a lecture!

*********************
*** GOOD LUCK !!! ***
*********************