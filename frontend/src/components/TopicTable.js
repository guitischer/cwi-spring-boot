import React, { useContext, useEffect } from 'react'
import MainContext from '../contexts/MainContext';
import topics_json from './topics.json'

function TopicTable() {

  const { topics, setTopics } = useContext(MainContext);

  useEffect(() => {
    setTopics(topics_json);
  },[]) 

  return (
    <div className="box">
    <table className='table is-fullwidth'>
      <thead>
        <tr>
          <th>Pauta</th>
          <th>Descrição</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
      {topics.map((topic) => (
        <tr key={topic.id}>
          <th>{topic.name}</th>
          <th>{topic.description}</th>
          <th><a href="#">Iniciar Votação</a>  | <a href="#">Votar</a></th>
        </tr>
      ))}
    </tbody>
    </table>
    <span className="icon">
  <i className="fas fa-home"></i>
</span>
    </div>
  )
}

export default TopicTable