import { Table } from "react-bootstrap";
import styled from "styled-components";

const handleColorType = color => {
  switch (color) {
    case "ERROR":
      return "#FFEBEE";
    case "INFO":
      return "#E3F2FD";
    case "WARNING":
      return "#F9FBE7";
    case "CRITICAL":
      return "#E57373"
    default:
      return "#fff";
  }
};

const StyledTr = styled.tr`
  background-color: ${({color}) => handleColorType(color)};
`

const SqlLogTable = (logs) => {
  return (
    <Table stripped bordered hover>
      <thead>
      <th>
        time
      </th>
      <th>
        level
      </th>
      <th>
        message
      </th>
      </thead>
      <tbody>
      {logs.logs.map(dataRow =>  {
        return(
          <StyledTr color={dataRow.level}>
            <td>
              {dataRow.time}
            </td>
            <td>
              {dataRow.level}
            </td>
            <td>
              {dataRow.message}
            </td>
          </StyledTr>
        )
      })}
      </tbody>
    </Table>
  )
}

export default SqlLogTable;