import React from 'react'
import { Link } from 'react-router-dom';

function RouteLink({name, url}) {

  return (
    <Link className='card-header-icon route-link' to={url}>
        <strong>{name}</strong>
    </Link>
  )
}

export default RouteLink