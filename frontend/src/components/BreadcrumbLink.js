import React, { useContext, useEffect } from 'react'
import { Link } from 'react-router-dom';
import MainContext from '../contexts/MainContext';

function BreadcrumbLink({name, url}) {
  const { setCurrUrl, currUrl } = useContext(MainContext);

  return (
    <Link onClick={() => {setCurrUrl(url)}} 
      className='card-header-icon route-link' to={url}>
        {url === currUrl ? <strong>{name}</strong> : name}
    </Link>
  )
}

export default BreadcrumbLink