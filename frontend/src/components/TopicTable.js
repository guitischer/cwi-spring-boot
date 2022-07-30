import React, { useContext, useEffect } from 'react'
import MainContext from '../contexts/MainContext';
import topics_json from './topics.json'
import RouteLink from './RouteLink';

function TopicTable() {

  const { topics, setTopics } = useContext(MainContext);

  useEffect(() => {
    setTopics(topics_json);
  },[setTopics]) 

  return (
    <div className="card">
      <header className="card-header">
        <p className="card-header-title">
          Pautas
        </p>
        
        <RouteLink name={'Nova Pauta'} url={'/add_topic'}/>
      </header>
      <div className="card-content">
        <table className='table is-fullwidth p-0'>
          <thead>
            <tr>
              <th width="30%">Pauta</th>
              <th width="40%">Descrição</th>
              <th width="30%">Ações</th>
            </tr>
          </thead>
          <tbody>
          {topics.map((topic) => (
            <tr key={topic.id}>
              <th>{topic.name}</th>
              <th>{topic.description}</th>
              <th><a href="\">Iniciar Votação</a>  | <a href="\">Votar</a></th>
            </tr>
          ))}
        </tbody>
        </table>
      </div>
    </div>
  )
}

export default TopicTable