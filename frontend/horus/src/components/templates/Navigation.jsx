import Sidebar from "../organisms/Sidebar";
import styled from "styled-components";

const Content = styled.div`
  margin-left: 50px;
  padding: 50px;
  height: 100%;
`

export const Navigation = ({children}) => (
  <>
    <Sidebar/>
    <Content>
      {children}
    </Content>
  </>
)