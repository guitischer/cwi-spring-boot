import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../services/api";

function AddTopic() {
  let navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    api
      .post("/topics", {
        name: name,
        description: description,
      })
      .then((response) => navigate("/", { replace: true }))
      .catch((err) => {
        console.error("Algo deu errado, erro:" + err);
      });
  };

  return (
    <>
      <h3 className="mt-2">Nova Pauta</h3>
      <form onSubmit={handleSubmit}>
        <div className="field">
          <label className="label">Nome</label>
          <div className="control">
            <input
              className="input"
              type="text"
              placeholder="Digite o nome da pauta..."
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            ></input>
          </div>
        </div>

        <div className="field">
          <label className="label">Descrição</label>
          <div className="control">
            <textarea
              className="textarea"
              placeholder="Digite a descrição da pauta..."
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            ></textarea>
          </div>
        </div>

        <div>
          <span
            className="button is-secondary mr-2"
            onClick={() => navigate("/", { replace: true })}
          >
            Voltar
          </span>
          <button className="button is-primary">Adicionar</button>
        </div>
      </form>
    </>
  );
}

export default AddTopic;
