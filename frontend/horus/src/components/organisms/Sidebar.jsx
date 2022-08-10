import React from "react";
import { Nav } from "react-bootstrap";
import styled from "styled-components";
import NavButton from "../atoms/NavButton";
import { Binoculars, Bug, Eyedropper, Sun } from "react-bootstrap-icons";
import { NavLink, useLocation } from "react-router-dom";

const Wrapper = styled.div`
  position: fixed;

  width: 50px;
  height: 100vh;
  
  border-right: 1px solid rgba(0, 0, 0, 0.11);
`

const NavLinks = styled.div`
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  padding: 10px;
`

const Sidebar = () => {
  const location = useLocation();
  return (
    <Wrapper>
      <Nav defaultActiveKey="/home" className="flex-column">
        <NavLinks>
          <NavLink exact to={"/home"}>
            <NavButton isActive={location.pathname === '/home'} Icon={Bug} />
          </NavLink>
          <NavLink exact to={"/sql-injection-scanner"}>
            <NavButton isActive={location.pathname === '/sql-injection-scanner'} Icon={Eyedropper} />
          </NavLink>
          <NavLink exact to={"/quick-scan"}>
            <NavButton isActive={location.pathname === '/quick-scan'} Icon={Binoculars} />
          </NavLink>
        </NavLinks>
      </Nav>
    </Wrapper>)
}

export default Sidebar;