import React, { useState } from 'react'
import { Link } from 'react-router-dom';

function AddTopic() {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (event) => {
    console.log(2);
    event.preventDefault();
  }

  return (
      <>
      <Link to={'/'}> &#9166;<strong> Voltar</strong> </Link>

      <h3 className='mt-2'>Nova Pauta</h3>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label className="label">Nome</label>
          <div className="control">
            <input className="input" type="text" placeholder="Digite o nome da pauta..." value={name} onChange={(e) => setName(e.target.value)}></input>
          </div>
        </div>

        <div className="field">
          <label className="label">Descrição</label>
          <div className="control">
            <textarea className="textarea" placeholder="Digite a descrição da pauta..." value={description} onChange={(e) => setDescription(e.target.value)}></textarea>
          </div>
        </div>
    
        <button className="button is-primary">Adicionar</button>
      </form>
      </>
  )
}

export default AddTopic