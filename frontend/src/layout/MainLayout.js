import React from 'react'

function MainLayout({children}) {

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
        </section>
        {children}
        </div>
      </div>
    </div>
  )
}

export default MainLayout