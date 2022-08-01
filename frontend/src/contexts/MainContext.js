import React, { createContext, useState } from 'react'

export const MainContext = createContext();

export function MainContextProvider({children}) {
  const [topics, setTopics] = useState([]);

  const values = {topics, setTopics}

  return (
    <MainContext.Provider value={values}>
      {children}
    </MainContext.Provider>
  )
}

export default MainContext;