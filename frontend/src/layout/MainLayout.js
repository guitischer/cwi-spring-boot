import React from 'react'

function MainLayout({children}) {

  return (
    <div className="container">
      <div className="columns is-centered">
        <div className="column is-two-thirds">
          <section className="hero is-primary header">
          <div className="hero-body">
            <p className="title">
              Desafio Técnico - Cooperativismo
            </p>
            <p className="subtitle">
              Spring Boot
            </p>
          </div>
        </section>
        <div className='content p-3'>
        {children}
        </div>
        </div>
      </div>
    </div>
  )
}

export default MainLayout