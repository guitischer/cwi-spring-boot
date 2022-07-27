import React from 'react'

function MainLayout({children}) {
  return (
    <div className="container">
      <div className="columns is-centered">
        <div className="column is-two-thirds">
        {children}
        </div>
      </div>
    </div>
  )
}

export default MainLayout