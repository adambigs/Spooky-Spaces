import CommentList from "./CommentList";

function Encounter({encounterId, description}){

    return(
        <>
        <div className="list-group-item text-center">
        {description} 
        </div>
        <div>
            <CommentList encounterId={encounterId}/>
        </div>
        </>
    );
}

export default Encounter;