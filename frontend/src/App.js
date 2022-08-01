import { Route, Routes } from 'react-router-dom';
import TopicTable from './components/TopicTable';
import { MainContextProvider } from './contexts/MainContext';
import MainLayout from './layout/MainLayout';
import AddTopic from './pages/topics/AddTopic';

import './styles/App.css';

function App() {
  
  return (
    <MainContextProvider>
      <MainLayout>
        <Routes>
          <Route path="/" element={<TopicTable/>} />
          <Route path="add_topic" element={<AddTopic />} />
        </Routes>
      </MainLayout>
    </MainContextProvider>
    
  )

}

export default App;
