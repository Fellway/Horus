import { MainTemplate } from "../components/templates/MainTemplate";
import { Col, Row } from "react-bootstrap";
import React from "react";
import styled from "styled-components";
import { Formik } from "formik";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { spinner, sqlAttackData, sqlAttackLogs, sqlAttackScan, sqlAttackStatus } from "../actions";
import { connect } from "react-redux";
import SqlLogTable from "../components/organisms/SqlLogTable";
import SqlData from "../components/organisms/SqlData";

const Description = styled.p`
  width: 50%;
`

class SqlInjectionScanner extends React.Component {
  
  componentDidUpdate(prevProps) {
    if (prevProps.sqlAttackStartScan?.taskId !== this.props.sqlAttackStartScan?.taskId) {
      this.checkScanStatus(this.props.sqlAttackStartScan?.taskId);
    }
  }
  
  async checkScanStatus(taskId) {
    const delay = ms => new Promise(
      resolve => setTimeout(resolve, ms)
    );
    
    this.props.sqlAttackStatus(taskId);
    await delay(1000);
    const status = this.props?.sqlAttackScanStatus?.status;
    if (status === 'running') {
      await delay(2000)
      await this.checkScanStatus(taskId)
    } else {
      this.props.sqlAttackData(taskId);
      this.props.sqlAttackLogs(taskId);
    }
  }
  
  render() {
    return (
      <MainTemplate>
        <div className="h-100">
          <Col>
            <h1>SQL Injection scanner</h1>
            <Description>A SQL injection is a technique that attackers use to gain unauthorized access to a web
              application database by adding a string of malicious code to a database query. A SQL injection (SQLi)
              manipulates SQL code to provide access to protected resources, such as sensitive data, or execute
              malicious SQL statements.</Description>
          </Col>
          <Row className={"justify-content-md-center pt-5"}>
            <Col md={4}>
              <Formik
                initialValues={{url: ''}}
                onSubmit={(values, {setSubmitting}) => {
                  setTimeout(() => {
                    setSubmitting(false);
                    this.props.spinner(true);
                    this.props.sqlAttackScan(values);
                  }, 400);
                }}>
                {({handleChange, handleBlur, values, handleSubmit, isSubmitting}) => (
                  <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-4" controlId="url">
                      <Form.Label>Attack url:</Form.Label>
                      <Form.Control onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.url}
                                    type="text"
                                    placeholder="Enter website url" />
                      <small className="text-muted">
                        Url should start with http:// or https://
                      </small>
                    </Form.Group>
                    <h4>Scan options:</h4>
                    <Form.Group className="mb-4">
                      <Form.Label>Cookie:</Form.Label>
                      <Form.Control onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.cookie}
                                    type="text"
                                    placeholder="Paste additional cookies" />
                      <Form.Check onChange={handleChange}
                                  onBlur={handleBlur} value={values.getBanner} id="getBanner"
                                  label={"Database banner"} />
                      <Form.Check onChange={handleChange}
                                  onBlur={handleBlur} value={values.getTables} id="getTables"
                                  label={"Database tables"} />
                      <Form.Check onChange={handleChange}
                                  onBlur={handleBlur} value={values.getColumns} id="getColumns"
                                  label={"Database columns"} />
                      <Form.Check onChange={handleChange}
                                  onBlur={handleBlur} value={values.getUsers} id="getUsers" label={"Users"} />
                    </Form.Group>
                    <div className={"col-md-12 text-center"}>
                      <Button className="btn-lg" variant="primary" type="submit" disabled={isSubmitting}>
                        Scan!
                      </Button>
                    </div>
                  </Form>
                )}
              </Formik>
            </Col>
          </Row>
          {!this.props.isLoading &&
          <>
            <Row className={"mt-4"}>
              {this.props.sqlAttackScanLogs?.log &&
              <>
                <h3>Scan logs</h3>
                <SqlLogTable logs={this.props.sqlAttackScanLogs.log} />
              </>
              }
            </Row>
            <Row className={"mt-4"}>
              {this.props.sqlAttackScanData?.data &&
              <SqlData data={this.props.sqlAttackScanData.data} />
              }
            </Row>
          </>
          }
          {this.props.isLoading &&
          <div className={"pt-5 text-center"}>
            <div className="spinner-border text-muted" role="status" />
          </div>
          }
        </div>
      </MainTemplate>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  sqlAttackLogs: (taskId) => dispatch(sqlAttackLogs(taskId)),
  sqlAttackData: (taskId) => dispatch(sqlAttackData(taskId)),
  sqlAttackStatus: (taskId) => dispatch(sqlAttackStatus(taskId)),
  sqlAttackScan: (attackUrl) => dispatch(sqlAttackScan(attackUrl)),
  spinner: (isLoading) => dispatch(spinner(isLoading))
})

const mapStateToProps = state => {
  const {sqlAttackStartScan, isLoading, sqlAttackScanStatus, sqlAttackScanData, sqlAttackScanLogs} = state;
  return {sqlAttackStartScan, isLoading, sqlAttackScanStatus, sqlAttackScanData, sqlAttackScanLogs};
};


export default connect(mapStateToProps, mapDispatchToProps)(SqlInjectionScanner);