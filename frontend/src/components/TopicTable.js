import React, { useContext, useEffect } from 'react'
import MainContext from '../contexts/MainContext';
import Button from './Button';
import topics_json from './topics.json'

function TopicTable() {

  const { topics, setTopics } = useContext(MainContext);

  useEffect(() => {
    setTopics(topics_json);
  },[setTopics]) 

  return (
    <div className="card">
      <header class="card-header">
        <p class="card-header-title">
          Pautas
        </p>
        <button className='card-header-icon'><a href="\"><strong>Nova Pauta</strong></a></button>
      </header>
      <div class="card-content">
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