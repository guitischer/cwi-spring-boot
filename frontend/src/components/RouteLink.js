import React, { useContext, useEffect } from 'react'
import { Link } from 'react-router-dom';
import MainContext from '../contexts/MainContext';

function RouteLink({name, url}) {
  const { setCurrUrl } = useContext(MainContext);

  return (
    <Link onClick={() => {setCurrUrl(url)}} 
      className='card-header-icon route-link' to={url}>
        <strong>{name}</strong>
    </Link>
  )
}

export default RouteLink