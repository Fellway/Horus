import { MainTemplate } from "../components/templates/MainTemplate";
import { Col, Container, ProgressBar, Row, Tab, Tabs } from "react-bootstrap";
import React from "react";
import styled from "styled-components";
import { Formik } from "formik";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { quickScan, spinner } from "../actions";
import { connect } from "react-redux";
import QuickScanResultCard from "../components/molecules/QuickScanResultCard";

const Description = styled.p`
  width: 50%;
`

const StyledH3 = styled.h3`
  text-align: right;
`

const QuickScan = (props) => {
  return (
    <MainTemplate>
      <div className="h-100">
        <Col>
          <h1>Quick scan</h1>
          <Description>Web crawlers copy pages for processing by a search engine, which indexes the downloaded pages so
            that users can search more efficiently. The goal of a crawler is to learn what webpages are about. This
            enables users to retrieve any information on one or more pages when it’s needed</Description>
        </Col>
        <Row className={"justify-content-md-center pt-5"}>
          <Col md={4}>
            <Formik
              initialValues={{websiteUrl: ''}}
              onSubmit={(values, {setSubmitting}) => {
                setTimeout(() => {
                  setSubmitting(false);
                  props.spinner(true);
                  props.quickScan(values.websiteUrl);
                }, 400);
              }}>
              {({handleChange, handleBlur, values, handleSubmit, isSubmitting}) => (
                <Form onSubmit={handleSubmit}>
                  <Form.Group className="mb-4" controlId="websiteUrl">
                    <Form.Label>Website url:</Form.Label>
                    <Form.Control onChange={handleChange}
                                  onBlur={handleBlur}
                                  value={values.websiteUrl}
                                  type="text"
                                  placeholder="Enter website url" />
                    <small className="text-muted">
                      Url should start with http:// or https://
                    </small>
                  </Form.Group>
                  <div className={"col-md-12 text-center"}>
                    <Button className="btn-lg" variant="primary" type="submit" disabled={isSubmitting}>
                      Quick scan!
                    </Button>
                  </div>
                </Form>
              )}
            </Formik>
          </Col>
        </Row>
        {props.quickScanResult &&
        <>
          <Container className={"mt-5"}>
            <div className={"mb-5"}>
              <Row>
                <Col>
                  <h3>High</h3>
                </Col>
                <Col>
                  <StyledH3>{props.quickScanResult.highRisk.length}</StyledH3>
                </Col>
              </Row>
              <ProgressBar className={"mb-4"} variant="danger"
                           now={props.quickScanResult.highRisk.length / props.quickScanResult.total * 100} />
              <Row>
                <Col>
                  <h3>Medium</h3>
                </Col>
                <Col>
                  <StyledH3>{props.quickScanResult.mediumRisk.length}</StyledH3>
                </Col>
              </Row>
              <ProgressBar className={"mb-4"} variant="warning"
                           now={props.quickScanResult.mediumRisk.length / props.quickScanResult.total * 100} />
              <Row>
                <Col>
                  <h3>Low</h3>
                </Col>
                <Col>
                  <StyledH3>{props.quickScanResult.lowRisk.length}</StyledH3>
                </Col>
              </Row>
              <ProgressBar className={"mb-4"} variant="info"
                           now={props.quickScanResult.lowRisk.length / props.quickScanResult.total * 100} />
              <Row>
                <Col>
                  <h3>Info</h3>
                </Col>
                <Col>
                  <StyledH3>{props.quickScanResult.infoRisk.length}</StyledH3>
                </Col>
              </Row>
              <ProgressBar className={"mb-4"} vartian="success"
                           now={props.quickScanResult.infoRisk.length / props.quickScanResult.total * 100} />
            </div>
          </Container>
         <Container className={"mb-5"}>
           <Tabs
             defaultActiveKey="lowRisk"
             id="risk-result-tabs"
             className="mb-3">
             <Tab eventKey="lowRisk" title={"Low (" + props.quickScanResult.lowRisk.length + ")"}>
               {props.quickScanResult.lowRisk.map(element => {
                 return (
                   <QuickScanResultCard title={element.title} description={element.description} recommendation={element.recommendation}  level={element.level}/>
                 )
               })}
             </Tab>
             <Tab eventKey="mediumRisk" title={"Medium (" + props.quickScanResult.mediumRisk.length + ")"}>
               {props.quickScanResult.mediumRisk.map(element => {
                 return (
                   <QuickScanResultCard title={element.title} description={element.description} recommendation={element.recommendation}  level={element.level}/>
                 )
               })}
             </Tab>
             <Tab eventKey="highRisk" title={"High (" + props.quickScanResult.highRisk.length + ")"}>
               {props.quickScanResult.highRisk.map(element => {
                 return (
                   <QuickScanResultCard title={element.title} description={element.description} recommendation={element.recommendation}  level={element.level}/>
                 )
               })}
             </Tab>
             <Tab eventKey="info" title={"Info (" + props.quickScanResult.infoRisk.length + ")"}>
               {props.quickScanResult.infoRisk.map(element => {
                 return (
                   <QuickScanResultCard title={element.title} description={element.description} recommendation={element.recommendation}  level={element.level}/>
                 )
               })}
             </Tab>
           </Tabs>
         </Container>
        </>
        }
        {props.isLoading &&
        <div className={"pt-5 text-center"}>
          <div className="spinner-border text-muted" role="status" />
        </div>
        }
      </div>
    </MainTemplate>
  );
}

const mapDispatchToProps = (dispatch) => ({
  quickScan: (url) => dispatch(quickScan(url)),
  spinner: (isLoading) => dispatch(spinner(isLoading))
})

const mapStateToProps = state => {
  const {quickScanResult, isLoading} = state;
  return {quickScanResult, isLoading};
};


export default connect(mapStateToProps, mapDispatchToProps)(QuickScan);