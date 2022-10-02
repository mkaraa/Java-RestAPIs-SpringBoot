### Quick Start
> git clone https://github.com/mkaraa/Java-RestAPIs-SpringBoot.git

  You need to run docker-compose file to up Postgresql Database with following command in project folder:

>     cd /src/main/resources

>     docker-compose -f ./docker-compose.yml up -d


- After running docker-compose, you can run Application in Intellij IDE


- Application will be running on  ``localhost:8081``
		
		
To connect a postgresql database : 

> user : postgres
	
> password : postgres
	
> port : default (5432)
					
					
** To insert data in databse you can use this .sql file:
>     /src/main/resources/data.sql    

* ER Diagram @ [https://github.com/mkaraa/Java-RestAPIs-SpringBoot/blob/main/src/main/resources/ER-Diagram.png]
(/src/main/resources/ER-Diagram.png)

 ### TECH STACK
     ** Java 11 
     ** Spring Boot 2.7.4
     ** Postgresql
