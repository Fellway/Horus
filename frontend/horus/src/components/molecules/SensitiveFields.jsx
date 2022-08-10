import styled from "styled-components";
import { Table } from "react-bootstrap";

const StyledTh = styled.th`
  padding: 5px;
`

const SensitiveFields = ({value}) => {
  return (
    <>
      <h4>Sensitive fields: </h4>
      <Table stripped bordered>
        <thead>
        <StyledTh>
          method
        </StyledTh>
        <StyledTh>
          parameter
        </StyledTh>
        <StyledTh>
          payloads
        </StyledTh>
        </thead>
        <tbody>
        {value.map(field => (
          <tr>
            <td>
              {field.place}
            </td>
            <td>
              {field.parameter}
            </td>
            <td>
              <Table stripped bordered>
                <thead>
                <th>
                  title
                </th>
                <th>
                  payload
                </th>
                <th>
                  match ratio
                </th>
                </thead>
                <tbody>
                {Object.values(field.data).map(payloadInfo => (
                  <tr>
                    <td>
                      {payloadInfo.title}
                    </td>
                    <td>
                      {payloadInfo.payload}
                    </td>
                    <td>
                      {payloadInfo.matchRatio}
                    </td>
                  </tr>
                ))}
                </tbody>
              </Table>
            </td>
          </tr>
        ))}
        </tbody>
      </Table>
    </>
  )
}

export default SensitiveFields;