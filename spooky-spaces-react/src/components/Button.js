import React from 'react'

function Button({ text, onClick }) {
    return (
    <button type="button" className="btn btn-outline-light" onClick={onClick}>{text}</button>

    )
}

export default Button;
