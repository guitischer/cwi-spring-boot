import React, { useContext, useEffect } from 'react'
import { Link } from 'react-router-dom'
import BreadcrumbLink from '../components/BreadcrumbLink';
import MainContext from '../contexts/MainContext';
function MainLayout({children}) {
  const { currUrl, setCurrUrl } = useContext(MainContext);

  return (
    <div className="container">
      <div className="columns is-centered">
        <div className="column is-two-thirds">
          <section className="hero is-primary header">
          <div className="hero-body">
            <p className="title">
              Desafio Técnico CWI
            </p>
            <p className="subtitle">
              Spring Boot
            </p>
          </div>
          <nav className="breadcrumb is-small" aria-label="breadcrumbs" style={{padding: '0rem 0rem 1rem 3rem'}}>
              <ul>
                <li><BreadcrumbLink name="Home" url="/"></BreadcrumbLink></li>
                <li><BreadcrumbLink name="Nova Pauta" url="/add_topic"></BreadcrumbLink></li>
              </ul>
            </nav>
        </section>
        {children}
        </div>
      </div>
    </div>
  )
}

export default MainLayout