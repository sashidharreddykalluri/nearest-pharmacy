### nearest-pharmacy
This is a REST API to retrieve the nearest pharmacy to a user provided coordinates.

<pre>
URL: http://localhost:8080/pharmacies/pharmacy-near-me?latitude=39.129845&longitude=-94.523424
</pre>

##Prerequisites
1. JAVA 8.0, Mysql 8.0 or later, Spring tool suite 4.0, maven
2. Make sure run the sql script(attached via email) in your local mysql install and then change the login configuration inside the application.properties file.

##Running the API
1. Import the cloned project to Spring tool suite as existing maven project, let the project get build (or to build manually run mvn clean install)
2. Go to the above mention URL and use different coordinates to test the API