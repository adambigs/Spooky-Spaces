# Capstone Project 
## Spooky Spaces 

   * This app is for tourists or locals of Milwaukee that are interested in ghost encounters/hauntings. With so much rich history in Milwaukee, and the gaining popularity of ghost sightings, there seems to be a growing desire to find these encounters. The difficulty of being a ghost hunter/ enthusiast is going to a new city and knowing where to begin. How does someone find something that may or may not exist? A scary good network of other ghost hunters, that’s how.  We have devised an app to solve that problem, and simultaneously generate a new scope of tourism revenue to our town, a ghost map.

---

# Divide 

## 1. Research
    1. Gathering local data/encounters (store in Excel/Google Table .csv to easily parse)
    2. Google API 
    3. Github Branches / pull requests / merging 
    4. Jira / github issues
    5. Sharing Pictures using JSON

## 2. The Backend 
    1. POM 
        - Dependencies 
        - application.properties
        - Test application.properties
    2. Models
    3. Data / Repository Layer (JDBC Template Repositories)
        - Mappers
        - Testing
    4. Domain
        - Service 
        - Testing 
            - Mockito or Doubles 
    5. Controllers
        - Security 

## 3. MySQL Database 
    1. Schema
        - tables
            - Post
                - Foreign Key IDs
            - Encounter
                - Location (GPS coordinates?)
                - Type of encounter
                - Picture (file name as String in sql maybe??)
            - Rating (maybe bridge table or included with comment post)
            - Comments as bridge table (one encounter to many comments)
            - Location 
            - Wish List
    2. DML (Test with HTTP requests and use SQL in the JDBC Templates)
        - Create
        - Read covered under DQL
        - Update
        - Delete 
    3. DQL 
        - Read
            - Establish what queries we need to make to show on our app
    4. Set up good known state (possible test database)
    5. Populate Database with researched cases 

## 4. Front End 
    1. Set Up
        - Install React, React-router-dom, jwt-decode on the project
        - Map out HTTP Requests for business plan (Goal verbalization)
        - Review SQL good state
    2. Structure when using app
        - Nav Bar
            - Home
            - About
            - View All
            - Add 
            - Update (Admin)
            - Delete (Admin)
            - Wish List
            - Sign Up / Log In
    3. Files/Component Structure
        - Public
            - Bootstrap or other CSS file
            - Favicon 
            - index.html
        - Components
            - Static 
                - About
                - NotFound           
            - encounter_comments 
            - Encounter
            - Wish List
            - Nav Bar (or do inside app)
            - Post List / Encounter List 
            - AuthContext
            - Errors
            - Login
            - SignUp
            - Update
            - Delete 
        - App
            - State(s) being managed
            - Functions 
            - Consts 
        - index

    
