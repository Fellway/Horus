import React from "react";
import { Table } from "react-bootstrap";
import SelectFillDataType from "../atoms/SelectFillDataType";

export const FormCheckerTable = ({inputs}) => (
  <Table>
    <thead>
    <tr>
      <th>#</th>
      <th>id</th>
      <th>value</th>
      <th>input type</th>
    </tr>
    </thead>
    <tbody>
    {inputs &&
    inputs.map((tableRow, i) => {
      return (
        <tr>
          <td>
            {i + 1}
          </td>
          <td>
            {tableRow.id}
          </td>
          <td>
            {tableRow.value}
          </td>
          <td>
            {tableRow.inputType}
          </td>
          <td>
            <SelectFillDataType/>
          </td>
        </tr>
      )
    })
    }
    </tbody>
  </Table>
)