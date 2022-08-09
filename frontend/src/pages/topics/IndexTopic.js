import React, { useContext, useEffect } from "react";
import MainContext from "../../contexts/MainContext";
import RouteLink from "../../components/RouteLink";
import api from "../../services/api";

function IndexTopic() {
  const { topics, setTopics } = useContext(MainContext);

  useEffect(() => {
    api
      .get("/topics")
      .then((response) => setTopics(response.data))
      .catch((err) => {
        console.error("Não foi possível carregar as pautas, erro:" + err);
      });
  }, [setTopics]);

  return (
    <div className="card">
      <header className="card-header">
        <p className="card-header-title">Pautas</p>
        <RouteLink name={"Nova Pauta"} url={"/add_topic"} />
      </header>
      <div className="card-content">
        <table className="table is-fullwidth p-0">
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
                <th>
                  <a href="\">Iniciar Votação</a> | <a href="\">Votar</a>
                </th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default IndexTopic;
