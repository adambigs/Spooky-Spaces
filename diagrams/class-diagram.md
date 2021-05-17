#Mastery
* idea
* src
    * main
        * java
            * learn.mastery

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
                    * Wish List
                        * List<Location>, username
                        * getters/setters

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

                    * Wish List Repository
                        * findByUserName();
                        * add();
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

                    * Wish List JDBC Repository
                        * findByUserName();
                        * add();
                        * delete();     
          
                * domain
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

                    * Wish List Repository
                        * findByUserName();
                        * add();
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
                            * Find All By Encounter
                            * Find By ID
                            * Add Encounter 
                            * Edit Encounter
                            * Delete Encounter
                    * Wish List Controller
                        * Top level CRUD    
                            * Find All By Username
                            * Find By ID
                            * Add Location
                            * Delete Location

            * Resources
                * mastery-config
    * test 
        * java
            * learn.mastery

                * data / repository
                  * Create Repository test for: 
                        * Encounter
                        * Location
                        * Comments
                        * Wish List

                * Service 
                  * Create Mockitos 
                        * Encounter
                        * Location
                        * Comments
                        * Wish List
* pom.xml
* README. md  