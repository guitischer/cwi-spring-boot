import TopicTable from './components/TopicTable';
import Button from './components/Button';
import MainLayout from './layout/MainLayout';

import './styles/App.css';

function App() {
  return (
      <MainLayout>
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

        <div className='is-flex is-flex-direction-column'>
          <Button text={"Nova Pauta"}/>
        </div>
        <TopicTable/>
      </MainLayout>
  )

}

export default App;
