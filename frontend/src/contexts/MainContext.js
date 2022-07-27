import React, { createContext, useState } from 'react'

export const MainContext = createContext();

export function MainContextProvider({children}) {
  const [topics, setTopics] = useState([]);
  return (
    <MainContext.Provider value={{topics, setTopics}}>
      {children}
    </MainContext.Provider>
  )
}

export default MainContext;