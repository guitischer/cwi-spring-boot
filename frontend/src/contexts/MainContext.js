import React, { createContext, useEffect, useState } from 'react'

export const MainContext = createContext();

export function MainContextProvider({children}) {
  const [topics, setTopics] = useState([]);
  const [currUrl, setCurrUrl] = useState('/');

  const values = {topics, setTopics, currUrl, setCurrUrl}

  return (
    <MainContext.Provider value={values}>
      {children}
    </MainContext.Provider>
  )
}

export default MainContext;