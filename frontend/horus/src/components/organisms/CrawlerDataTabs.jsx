import React, { useState } from "react";
import Tabs from 'react-bootstrap/Tabs';
import { Tab } from "bootstrap";
import { DataTable } from "./Table";
import { Table } from "react-bootstrap";
import styled from "styled-components";
import ImageModal from "../molecules/ImageModal";

const Img = styled.img`
  height: 50px;
  width: 50px;
  object-fit: cover;
`

const StyledTd = styled.td`
  text-align: center;
`

export const CrawlerDataTabs = ({defaultTab, tabs, data}) => {
  const [key, setKey] = useState(defaultTab);
  const [show, setShow] = useState(false);
  const [imgId, setImgId] = useState(null);
  const handleClose = () => setShow(false);
  const handleShow = (imageId) => {
    setShow(true);
    setImgId(imageId);
  }
  
  return (
    <>
      <ImageModal imgId={imgId} show={show} onHide={handleClose}>
      </ImageModal>
      {tabs && <Tabs
        id="controlled-tab"
        activeKey={key}
        onSelect={(k) => setKey(k)}
        className="mb-3"
      >
        <Tab eventKey="urls" title="Urls">
          <DataTable columns={['no', 'url']} data={data.urls}>
          </DataTable>
        </Tab>
        <Tab eventKey="links" title="Links">
          <DataTable columns={['no', 'url']} data={data.links}>
          </DataTable>
        </Tab>
        <Tab eventKey="scripts" title="Scripts">
          <DataTable columns={['no', 'url']} data={data.scripts}>
          </DataTable>
        </Tab>
      </Tabs>
      }
    </>
  )
}