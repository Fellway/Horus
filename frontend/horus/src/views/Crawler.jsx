import React from 'react';
import { Formik } from "formik";
import Form from 'react-bootstrap/Form';
import { Col, Row } from "react-bootstrap";
import styled from "styled-components";
import Button from "react-bootstrap/Button";
import { CrawlerDataTabs } from "../components/organisms/CrawlerDataTabs";
import { crawlWebsite, spinner } from "../actions";
import { connect } from "react-redux";

const Description = styled.p`
  width: 50%;
`

const Crawler = (props) => (
  <>
    <div className="h-100">
      <Col>
        <h1>Website crawler</h1>
        <Description>Web crawlers copy pages for processing by a search engine, which indexes the downloaded pages so
          that users can search more efficiently. The goal of a crawler is to learn what webpages are about. This
          enables users to retrieve any information on one or more pages when it’s needed</Description>
      </Col>
      <Row className="justify-content-md-center pt-5">
        <Col md={4}>
          <Formik
            initialValues={{websiteUrl: '', depth: 0}}
            onSubmit={(values, {setSubmitting}) => {
              setTimeout(() => {
                setSubmitting(false);
                props.spinner(true);
                props.crawlWebsite(values.websiteUrl, values.depth);
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
                <Form.Group className="mb-4" controlId="depth">
                  <Form.Label>Scan depth:</Form.Label>
                  <Form.Control onChange={handleChange}
                                onBlur={handleBlur}
                                value={values.depth}
                                type="number"
                                placeholder="0"
                                min="0"
                  />
                  <small className="text-muted">
                    If depth is set to 0, the application will scan the entire website.
                  </small>
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
      <Row>
        <CrawlerDataTabs tabs={['urls', 'links', 'scripts']} data={props.crawler} />
      </Row>
      {props.isLoading &&
      <div className={"pt-5 text-center"}>
        <div className="spinner-border text-muted" role="status" />
      </div>
      }
      {!props.isLoading && props.crawler.length === 0 &&
      <div className={"pt-5 text-center"}>
        <h2 className={"text-muted"}>Scan the page to see data.</h2>
      </div>}
    </div>
  </>
)

const mapDispatchToProps = (dispatch) => ({
  crawlWebsite: (url, depth) => dispatch(crawlWebsite(url, depth)),
  spinner: (isLoading) => dispatch(spinner(isLoading))
})

const mapStateToProps = state => {
  const {crawler, isLoading} = state;
  return {crawler, isLoading};
};

export default connect(mapStateToProps, mapDispatchToProps)(Crawler);