import { useState, useEffect } from 'react';
import { useHistory, useParams } from 'react-router-dom';

function AddEncounter() {
    const [description, setDescription] = useState("");
    const [encounters, setEncounters] = useState([]); //List of all encounters
    const [messages, setMessages] = useState(""); //Any error messages
    
    const { id } = useParams();

    const history = useHistory();

    const handleAdd = (event) => {
      event.preventDefault();
      event.stopPropagation();
    
      let canAdd = true;

      if (description.trim().length === 0){
        canAdd = false;
      }

      if (canAdd) { //set all values from the encounter
        let encounter = {};
        encounter["description"] = description;
        encounter["locationId"] = id;
        addEncounter(encounter);
      }
    }

    const addEncounter = (encounter) => {
        let canSet = true;
  
        for (let i = 0; i < encounters.length; i++) { //make sure the encounterId does not already exist
          if (encounter.encounterId === encounters[i].encounterId) {
            canSet = false;
          }
        }
  
        if (canSet) {
        addFetch(encounter); //add encounter if no errors
        } else {
        setMessages("Encounter Id is taken");
        } 
    }

    useEffect(() => { //get the list of all encounters
      fetch("http://localhost:8080/api/encounter")
      .then(response => {
        if (response.status !== 200) {
        console.log(response);
        return Promise.reject("GET didn't work");
        }
        return response.json();
      })
      .then(json => setEncounters(json))
      .catch(console.log);
  }, []);

  const addFetch = (encounter) => { //add an encounter
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(encounter)
    };

    fetch("http://localhost:8080/api/encounter", init)
      .then(response => {
        if (response.status !== 201) {
          return Promise.reject("POST doesn't work");
        }
        return response.json();
      })
      .then(json => {
        setEncounters([...encounters, json]);
        setMessages("");
      })
      .then(history.goBack()) 
      .catch(console.log);
  }

  const handleDescriptionChange = (event) => { //get the vlaues from the form
    setDescription(event.target.value);
  }

  return ( //form to get values for adding an encounter
    <div className="card">
      <h2 className="card-title ml-3">Add Encounter</h2>
      <div className="card-body">
        <form onSubmit={handleAdd}>
          <div className="form-group">
            <label htmlFor="descriptionTextBox">Description:</label>
            <input type="text" id="descriptionTextBox" onChange={handleDescriptionChange} className="form-control"/>
          </div>
          <button type="submit" className="btn btn-primary mt-2">Add</button>
        </form>
      </div>
    </div>
  );
}

export default AddEncounter;