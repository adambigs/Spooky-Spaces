import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Home from "./components/Home";
import About from "./components/About";
import NotFound from "./components/NotFound";
import LocationList from "./components/LocationList";
import Location from "./components/Location";


function App() {
  return (
    <div className="App">
        <Router>
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/about">
              <About />
            </Route>
            <Route path="/location/:id">
              <LocationList />
            </Route>
            <Route path="/encounters">
            
            </Route>
            <Route path="*">
            <NotFound />
            </Route>
          </Switch>
        </Router>
    </div>
  );
}

export default App;
