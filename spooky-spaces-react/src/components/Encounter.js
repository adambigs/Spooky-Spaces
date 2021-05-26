import { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import CommentList from './CommentList';
import Button from './Button';


function Encounter({encounterId, description, encounterType, deleteEncounter}){

    const defaultEncounter = {
      encounterId: 0,
      description: "",
      encounterType: 0,
      locationId: 0
    }

    const [encounter, setEncounter] = useState(defaultEncounter);
    const [isActive, setIsActive] = useState("true");
    // const [locationId, setLocationId] = useState();

    const history = useHistory();

    const handleActive = () => {
      setIsActive(!isActive);
    }
    
    const deleteById  = ()  => {
      fetch(`http://localhost:8080/api/encounter/${encounterId}`, { method: "DELETE" })
        .then((response) => {
          if (response.status === 204) {
            deleteEncounter(encounterId);
          } else if (response.status === 404) {
            return Promise.reject("Comment");
          } else {
            return Promise.reject(
              `Delete failed with status: ${response.status}`
            );
          }
        })
        // .then(history.push(`/location/${encounter.locationId}`))
        .catch(console.log);
    };

    useEffect (() => { //Get encounter by id
      fetch(`http://localhost:8080/api/encounter/${encounterId}`)
      .then(response => response.json())
      .then(data => setEncounter(data))
      .catch(error => console.log(error));
    }, []); //THERE BRACKETS LOSE THEIR MINDS

    return(
        <>
        <div className="list-group-item text-center">
        <Link to={`/encounter/edit/${encounterId}`}><Button text="Edit"/></Link>
        <button className="btn btn-secondary ml-2"onClick={deleteById}>Delete</button>
        <p>Type: {encounter.encounterType}</p>
        <p>{description}</p> 
        </div>
        <div>
        <Link to={`/comment/add/${encounterId}`}><Button text="Add Comment" /></Link>
        <button type="submit" className="btn btn-outline-success" onClick={handleActive}>View Comments</button>
        <span className={isActive ? "d-none" : null}>
            <CommentList encounterId={encounterId}/>
            </span>
        </div>
        </>
    );
}

export default Encounter;