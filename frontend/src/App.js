import { Route, Routes } from "react-router-dom";
import IndexTopic from "./pages/topics/IndexTopic";
import { MainContextProvider } from "./contexts/MainContext";
import MainLayout from "./layout/MainLayout";
import StoreTopic from "./pages/topics/StoreTopic";

import "./styles/App.css";

function App() {
  return (
    <MainContextProvider>
      <MainLayout>
        <Routes>
          <Route path="/" element={<IndexTopic />} />
          <Route path="add_topic" element={<StoreTopic />} />
        </Routes>
      </MainLayout>
    </MainContextProvider>
  );
}

export default App;
