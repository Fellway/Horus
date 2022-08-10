import styled from "styled-components";

const Wrapper = styled.button`
  padding: 0;
  height: 50px;
  width: 100%;
  border: none;
  cursor: pointer;
  background-color: transparent;
  font-size: 25px;
  transition: .2s;
`;

const NavButton = ({isActive, Icon, color}) => (
  <Wrapper isActive={isActive} color={color}>
      <Icon/>
  </Wrapper>
);

export default NavButton;