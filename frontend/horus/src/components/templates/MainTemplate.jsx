import React from "react";
import { Navigation } from "./Navigation";


export const MainTemplate = ({children}) => (
  <>
    <Navigation>
      {children}
    </Navigation>
  </>
)
