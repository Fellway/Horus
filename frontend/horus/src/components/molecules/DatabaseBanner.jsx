import styled from "styled-components";

const Wrapper = styled.p`
  width: fit-content;
  padding: 20px;
  margin: 0 auto;
`

const DatabaseBanner = ({value}) => {
  return (
    <>
      <h4>
        Database banner:
      </h4>
      <Wrapper>
        {value}
      </Wrapper>
    </>
  )
}

export default DatabaseBanner;