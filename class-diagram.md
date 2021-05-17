#Mastery
* idea
* src
    * main
        * java
            *learn.mastery
                * models
                    * Encounter
                        * Fields:
                            * encounter_id, description, picture, type, location
                            * getter / setters
                            * validation @annotations
                    * Type (ENUM)
                        * Visual
                        * Auditory
                        * Touch
                        * Temperature 
                    * Location
                        * location_id, address, latitude, longitude 
                        * getter/setters
                        * validation @annotations
                    * Comments 
                        * Comment_id, comment, rating    
                        * getter/setters
                        * validation @annotations
                * repository / data
                    * DataException (extends Exception)
                        * constructor DataException(String message, Throwable cause)

                    * Encounter Repository
                        * findAll();
                        * findById();
                        * add();
                        * update();
                        * delete();

                    * Location Repository
                        * findAll();
                        * findById();
                        * add();
                        * update();
                    
                    * Comments Repository
                        * findAllByEncounter();
                        * findById();
                        * add();
                        * update();
                        * delete();

                    * Encounter JDBC Repository
                        * findAll()
                        * findByType();
                        * findById();
                        * Add()
                        * Edit()
                        * delete()

                    * Location JDBC Repository (maybe ask kiel)
                        * findAll();
                        * findById();
                        * Add()
                        * Edit()
                        * delete()
          
                    * Comments JDBC Repository
                        * findAllByEncounter();
                        * findAll();
                        * findById();
                        * Add();
                        * Edit();
                        * delete();
          
                *domain
                    * Encounter Service
                        * findAll();
                        * findById();
                        * add();
                        * update();
                        * delete();

                    * Location Service
                        * findAll();
                        * findById();
                        * add();
                        * update();
                    
                    * Comments Service
                        * findAllByEncounter();
                        * findAll();
                        * findById();
                        * add();
                        * update();
                        * delete();

                    * Result
                        * field 
                            * message 
                        * isSuccess()
                        * getErrorMessage()
                        * addErrorMessage(String message)    
                    
                    * Result Type ENUM

                * Controller
                    * Encounter Controller
                        * Top level CRUD    
                            * Find All
                            * Find By ID
                            * Add Encounter 
                            * Edit Encounter
                            * Delete Encounter
                    * Locations Controller
                        * Top level CRUD    
                            * Find All
                            * Find By ID
                            * Add (revisit)
                    * Comments Controller
                        * Top level CRUD    
                            * Find ALl By Encounter
                            * Find By ID
                            * Add Encounter 
                            * Edit Encounter
                            * Delete Encounter
            * Resources
                * mastery-config
    * test 
        * java
            * learn.mastery
              
                * domain
                  * Create Service Test for:
                    * Encounter
                    * Location
                    * Comments
                  
                * data / repository
                  * Create Repository test for: 
                        * Encounter
                        * Location
                        * Comments
                  * Create Mockitos 
                        * Encounter
                        * Location
                        * Comments
* pom.xml
* README.md  