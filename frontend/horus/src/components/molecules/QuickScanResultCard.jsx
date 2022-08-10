import Card from "react-bootstrap/Card";

import styled from "styled-components";

const handleColorType = color => {
  switch (color) {
    case "LOW":
      return "#0dcaf0";
    case "MEDIUM":
      return "#ffc107";
    case "HIGH":
      return "#dc3545";
    case "INFO":
      return "#198754";
    default:
      return "#fff";
  }
};

const RiskDot = styled.div`
  width: 15px;
  height: 15px;
  display: inline-block;
  border-radius: 50%;
  margin-right: 20px;
  background-color: ${({color}) => handleColorType(color)};
`

const StyledHeader = styled.h5`
  color: #12638e;
`

function QuickScanResultCard({title, description, recommendation, level}) {
  return (
    <Card className={"mb-3"} style={{width: '100%'}}>
      <Card.Body>
        <Card.Title className={"mb-4"}>
          <>
            <RiskDot color={level} />{title}
          </>
        </Card.Title>
        <StyledHeader>
          Risk description:
        </StyledHeader>
        <Card.Text>{description}</Card.Text>
        <StyledHeader>
          Recommendation:
        </StyledHeader>
        <Card.Text>{recommendation}</Card.Text>
      </Card.Body>
    </Card>
  )
}

export default QuickScanResultCard;