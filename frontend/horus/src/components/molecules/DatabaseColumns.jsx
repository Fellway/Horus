import { Table } from "react-bootstrap";

const DatabaseColumns = ({value}) => {
  const schemas = Array.of(value);
  
  return (
    <>
      <h4>Database columns:</h4>
      {schemas.map(schema => (
        <Table stripped bordered>
          <thead>
          {Object.values(schema).map(t => (
            Array.of(Object.keys(t))).map(tables => (
              tables.map(table => (
                <th>
                  {table}
                </th>
              ))
             
            )
          ))}
          </thead>
          <tbody>
          {Object.values(schema).map(c => (
            <tr>
              {Object.values(c).map(column => (
                <td>
                  {Object.entries(column).map(([key, val]) => {
                    return <div>{key} {val}</div>
                  })}
                </td>
              ))}
            </tr>))
          }
          </tbody>
        </Table>))}
    </>
  )
}

export default DatabaseColumns;