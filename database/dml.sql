use spooky_spaces;

insert into location 
(location_name, address, latitude, longitude)
values 
('Technology Innovation Center (Muirdale Sanitarium)', '10437 W Innovation Dr, Wauwatosa, WI', '43.04368198', '-88.04444294'),
('Pfister Hotel', '424 E Wisconsin Ave', '43.03956219', '-87.90551367'),
('Pabst Mansion', '2000 W Wisconsin Ave', '43.0392265', '-87.93791812'),
('Shaker''s', '422 S Second St', '43.02709626', '-87.91223392'),
('Sabbatic', '700 S Second St', '43.024537', '-87.91234892'),
('Marian Center for Nonprofits', '3195 S Superior St', '42.98614047', '-87.87074732'),
('Brumder Mansion', '3046 W Wisconsin Ave', '43.03943369', '-87.95283152'),
('The Riverside Theater', '116 W Wisconsin Ave', '43.03900341', '-87.9111033'),
('Historic Miller Caves', '3897 W State St', '43.04231145', '-87.96216669'),
('Marquette University', '1250 W Wisconsin Ave', '43.039324', '-87.92795602'),
('North Point Lighthouse', '2650 N Wahl Ave', '43.06580298', '-87.87136542'),
('Milwaukee Public Museum', '800 W Wells St', '43.04115971', '-87.92084314'),
('The Rave', '2401 W Wisconsin Ave', '43.03832494', '-87.94314067'),
('Modjeska Theater', '1134 W Historic Mitchell St', '43.01287596', '-87.92650196'),
('Hilton Garden Inn', '611 N Broadway', '43.03801065', '-87.90791599'),
('Ambassador Hotel', '2308 W Wisconsin Ave', '43.03955649', '-87.94191908'),
('Skylight Music Theater', '158 N Broadway', '43.03238409', '-87.90675348'),
('Sunset Playhouse', '700 Wall St, Elm Grove, WI', '43.04050189', '-88.07807748');

insert into encounter
(encounter_description, location_id, encounter_type)
values
('Built in 1915. It was formerly a treatment center for people with tuberculosis, many of whom passed away. Employees report hearing coughing in rooms.', 1, 2),
('Built in the 19th century, the hotel is apparently haunted by its namesake, Charles Pfister, who likes to haunt MLB players staying in the hotel.', 2, 1),
('Respondents described doors opening and closing, chandeliers shaking and objects falling to the floor. One witness reported a cold breeze that made her legs immobile. Another described the perfumed whiff of an unseen spirit brushing past her.', 3, 1),
('Since Shaker’s opened in 1986, staff and customers alike have recounted stories of inexplicable occurrences and otherworldly sightings: Lights with fried bulbs turn on and off by themselves, doors close by unseen hands, and figures appear and disappear. Employees say they have all experienced something terrifying at the business, from pianos playing by themselves to objects flying off shelves. Researchers say the bar was built over a cemetery, which could explain some of the unsettling activity.', 4, 1),
('It was bartender Matty Gonzales who saw the ghost in February 2010. While closing up, he had just taken out the trash and locked the doors. He saw a skinny old man in a suit with long gray hair standing inside the building. He wondered how the man had gotten in and was about to throw him out when the person moved toward him without moving his legs. The bartender grabbed his coat, set the alarm and left without closing his till for the night.', 5, 1),
('Linda Mrochinski, operations manager, recounts the story of one tenant who was locking up for the night in Rosary Hall when she heard fabric swishing. Peering toward the Clare Wing, she saw a nun in full habit walking toward her. Noticeably absent were the sounds of footsteps. The sister passed through the doorway connecting Rosary and Loretto halls, but when the tenant followed, she found the doors, as they always were, still locked.', 6, 1),
('The historic home was once owned by brothers Sam and Ed Pick – who worked for notorious gangster Al Capone. The property became a hub for gambling, illegal drinking, parties and prostitution. Some paranormal investigators believe that characters who worked at the mansion during the speakeasy era continue to roam the halls to this day.', 7, 1),
('Legend has it that at least one male and one female entity call the theater home. Employees, artists and visitors have reported sightings, moving objects, the feeling of being watched while alone, and a strong scent of either perfume or cigar smoke.', 8, 1),
('The Historic Miller Caves, which have a fascinating back story of their own, are supposedly haunted by the ghosts of two 19th century lovers who once used the caves for their secret meetings. A young man reportedly died after falling and hitting his head in a stairwell. Legend has it that his lover died weeks later, due to a broken heart.', 9, 1),
('Carpenter Hall, Johnston Hall, Mashuda Hall, Schroeder Hall, Straz Tower and Varsity Theatre are just some of the locations on the Marquette University campus that have long been rumored to be haunted. Ghostly residents reportedly include a former hospital patient who rides the elevator, unexplained children who appear in the pool, and Jesuit priests who visit their old workplace.', 10, 1),
('Volunteers say many people have heard the sound of kids laughing throughout the lighthouse, while getting the feeling that they are not alone or welcome.', 11, 2),
('Milwaukee Public Museum is haunted by its former director, Dr. Stephan Francis Borhegyi (1921–1969). Doctor Borhegyi, who died in a car crash in 1969, has been known to spend time on the third floor of the building near his official portrait.', 12, 1),
('The Rave started out as the home of the fraternal order of the Eagles (hence “Eagles Ballroom”) when it was built in 1927. It included a gymnasium, bowling alley, and swimming pool in the basement. Rumor is that it is haunted by various people: children who drowned in the pool, an abusive director of the shelter that was once housed here, and even Buddy Holly, who played one of his last concerts here. Touring bands often request tours of the basement and swimming pool area... or just find their way down there themselves.', 13, 1),
('The Modjeska hosted major vaudeville acts when it was opened in 1910. Over the years, many employees and guests have claimed to see floating orbs of light and people out of the corners of their eye, although the two most common sights are of a nonexistent woman wearing white on the stage, and a man appearing regularly in the balcony.', 14, 1),
('Built in 1886, the Loyalty Building, which was recently converted to a hotel, was built on the site of the Newhall Hotel fire. On the night of January 10th, 1883, more than 70 people died when a fire spread from the basement of the hotel up through an elevator shaft. Today, those lost souls still allegedly inhabit the building, manifesting themselves most often in rooms 326 and 201, where employees repeatedly report strange sounds, and bathroom doors opening and closing on their own.', 15, 1),
('Unfortunately, no matter the glitz, glamour, and comfort that the hotel offers, you will find that the hotel can’t get past its most famous association, that with Jeffrey Dahmer. The hotel was the scene of Dahmer’s first Milwaukee murder, who he killed in his room before spiriting the body out of his room in a suitcase. People in other rooms across the hotel have mentioned strange occurrences. Before housekeeping has had time to visit some of the rooms, beds get made or unmade. Items have been known to disappear, only to reappear later and sometimes in another place.', 16, 1),
('It’s a story with the passion of an opera. Skylight Opera founder Clair Richardson (d. 1980) works hard to establish his opera company and as his last wish, states that he wishes to be buried underneath the stage. "The reason is that he wanted his successors to know their decisions were being made over his dead body." Allison says. "That’s his joke, not mine." When the company moved from Jefferson Street to Broadway in 1993, a New Orleans’ style funeral procession moved from the old location to the new. Clair’s assistant, Bo Black, carried his urn and it was placed underneath the new stage in the trap door area.On every show’s checklist is to make sure a spotlight is functioning and focused on the urn downstairs. If it isn’t, a restless ghost is said to tamper with the lighting equipment. Theater superstition or paranormal?', 17, 1),
('The Sunset Playhouse in Elm Grove has supposedly been haunted by one of its actors since 1968. Lester Schulz, who died of a heart attack as he was leaving the stage at the playhouse, has been accepted by everyone at Sunset Playhouse, and allegedly plays pranks on people there regularly.', 18, 1);

insert into comments
(username, rating, comment_text, encounter_id)
values ('cooldude69', '5', 'This place was so spooky. I was scared.', 1);
