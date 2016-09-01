# Hibernate-Bolb

This program demonstrates how to save and get Blob from Database in Hibernate:

Database: Mysql 5.6
Hibernate: 4.3.6

Project Structure:

src
 com.harvey.Entity:
	    Student.java
	    Student.hbm.xml

 com.harvey.Util
	    HibernateUtil.java
	    NoEntityException.java
 hibernate.cfg.xml

test
 com.harvey.JunitTest
     HibernateTest.java


How to use:
	1. config your own database connection settings in hibernate.cfg.xml
	2. run "saveBlob" function in HibernateTest.java first to save a blob 
	3. run "loadBlob" function in HibernateTest.java
