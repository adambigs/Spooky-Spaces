import AuthContext from './AuthContext';
import { useContext} from "react";

function Logout() {
    const auth = useContext(AuthContext);

    return(
        auth.Logout
    );
}

export default Logout;