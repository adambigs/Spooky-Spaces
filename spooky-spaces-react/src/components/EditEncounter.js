import { useEffect,useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';

function UpdateEncounter() {
  const defaultEncounter = {
    encounterId: 0,
    description: "",
    encounterType: 0,
    locationId: 0
  };

  const [description, setDescription] = useState('');
  const [encounterType, setEncounterType] = useState(0);
  const [encounter, setEncounter] = useState(defaultEncounter);

  const { id } = useParams();
  const history = useHistory();

  useEffect(() => {
    fetch(`http://localhost:8080/api/encounter/${id}`)
      .then(response => response.json())
      .then(data => setEncounter(data))
      .catch(error => console.log(error));
  });

  const handleUpdate = (event) => {
    event.preventDefault();

    const newEncounter = { //set the values for the updated encounter
      encounterId: encounter.encounterId,
      description: description,
      encounterType: encounterType,
      locationId: encounter.locationId
    }

    if (newEncounter.description.length === 0){ //If not updated, set to original values
      newEncounter.description = encounter.description ;
    }
    if (newEncounter.encounterType === 0){
        newEncounter.encounterType = encounter.encounterType;
    }

    const init = { //use PUT to update the encounter
      method: "PUT",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
      body: JSON.stringify(newEncounter)
    };

    fetch(`http://localhost:8080/api/encounter/${id}`, init)
      .then(response => {
        if (response.status !== 204) {
          return Promise.reject("Couldn't update");
        }
      })
      .then(history.goBack()) 
      .catch(console.log)
  }

  const handleDescriptionChange = (event) => { //if the value is changed, use the new value
    setDescription(event.target.value);
  }
  const handleEncounterTypeChange = (event) => {
    setEncounterType(event.target.value);
  }

  return ( //form for submiting updates to the encounter
    <div className="container">
    <form onSubmit={handleUpdate}>
      <div className="form-group">
        <label htmlFor="encounterTextBox">EncounterId:</label>
        <input type="text" id="encounterTextBox" className="form-control" readOnly="readOnly" value={encounter.encounterId}/>
      </div>
      <div className="form-group">
        <label htmlFor="descriptionTextBox">Description:</label>
        <input type="text" id="descriptionTextBox" className="form-control" onChange={handleDescriptionChange} />
      </div>
      <div className="form-group">
            <label htmlFor="encounterTypeDropDown">Encounter Type:</label>
            <select id="encounterTypeDropDown" onChange={handleEncounterTypeChange} className="form-control">
              <option value={1}>Visual</option>
              <option value={2}>Auditory</option>
              <option value={3}>Touch</option>
              <option value={4}>Temperature</option>
            </select>
          </div>
      <button type="submit" className="btn btn-primary mt-2">Update</button>
    </form>
    </div>    
  );
}

export default UpdateEncounter;