import Modal  from "react-bootstrap/Modal";
import React from "react";
import styled from "styled-components";

const ModalDialogStyled = styled.div`
  width: 100vw;
  top:50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 100vh;
  position: fixed;
`

function ImageModal({imgId, show, onHide}) {
  return(
      <Modal size="lg" show={show} onHide={onHide}>
        <Modal.Header closeButton>
        
        </Modal.Header>
        <Modal.Body>
          <img className="img-fluid" src={"tmp/" + imgId + ".jpg"}/>
        </Modal.Body>
      </Modal>
  )
}

export default ImageModal;