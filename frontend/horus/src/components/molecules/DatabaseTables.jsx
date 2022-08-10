import { Table } from "react-bootstrap";

const DatabaseTables = ({value}) => {
  const keys = Object.keys(value);
  const values = Object.values(value);
  return (
    <>
      <h4>Database tables:</h4>
      <Table stripped bordered>
        <thead>
        {keys.map(k => (
          <th>
            {k}
          </th>
        ))}
        </thead>
        <tbody>
        <tr>
          {values.map(v => (
            <td>
              {v.map(table => (
                <div>{table}</div>
              ))}
            </td>
          ))}
        </tr>
        </tbody>
      </Table>
    </>
  )
}

export default DatabaseTables;