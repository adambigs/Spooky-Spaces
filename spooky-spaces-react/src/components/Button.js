import React from 'react'

function Button({ text, onClick }) {
    return (
    <button type="button" className="btn btn-outline-light mx-1" onClick={onClick}>{text}</button>

    )
}

export default Button;
