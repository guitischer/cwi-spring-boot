import React from 'react'

function Button({text}) {
  return (
    <input className="button btn is-primary is-outlined is-responsive" type="button" value={text} />
  )
}

export default Button