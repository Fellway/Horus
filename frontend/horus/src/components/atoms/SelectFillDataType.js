import Form from "react-bootstrap/Form";

function SelectFillDataType() {
  return (
    <Form.Select aria-label="Default select example">
      <option value="LEAVE_EMPTY">Leave input empty</option>
      <option value="USER_INPUT">Fill data manually</option>
      <option value="SQL_INJECTION_DICTIONARY">Use sql injection dictionary</option>
      <option value="XSS_INJECTION_DICTIONARY">Use xss injection dictionary</option>
      <option value="HTML_INJECTION_DICTIONARY">Use html injection dictionary</option>
    </Form.Select>
  )
}

export default SelectFillDataType;