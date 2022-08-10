import React from 'react';
import { Table } from "react-bootstrap";

export const DataTable = ({columns, data}) => (
  <>
    {data && <Table striped bordered hover>
      <thead>
      <tr>
        {columns.map((column => {
          return (
            <th>
              {column}
            </th>
          )
        }))}
      </tr>
      </thead>
      <tbody>
      {data.map((dataRow, i) => {
        return (
          <tr>
            <td>
              {i+1}
            </td>
            <td>
              <a target="_blank" href={dataRow}>{dataRow}</a>
            </td>
          </tr>
        )
      })}
      </tbody>
    </Table>}
  </>
);

