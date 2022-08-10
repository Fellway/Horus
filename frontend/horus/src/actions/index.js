import axios from 'axios';

export const CRAWL_SUCCESS = 'CRAWL_SUCCESS';
export const CRAWL_FAILURE = 'CRAWL_FAILURE';
export const LOAD_FORMS_SUCCESS = 'LOAD_FORM_SUCCESS';
export const LOAD_FORM_FAILURE = 'LOAD_FORM_FAILURE';
export const QUICK_SCAN_SUCCESS = 'QUICK_SCAN_SUCCESS';
export const QUICK_SCAN_FAILURE = 'QUICK_SCAN_FAILURE';
export const SQL_ATTACK_START_SCAN_SUCCESS = 'SQL_ATTACK_SCAN_SUCCESS';
export const SQL_ATTACK_SCAN_STATUS = 'SQL_ATTACK_SCAN_STATUS';
export const SQL_ATTACK_SCAN_DATA_SUCCESS = 'SQL_ATTACK_SCAN_STATUS_SUCCESS';
export const SQL_ATTACK_SCAN_LOGS_SUCCESS = 'SQL_ATTACK_SCAN_LOGS_SUCCESS';
export const FINALLY = 'FINALLY';
export const LOADER = 'LOADER';

export const crawlWebsite = (url, depth) => (dispatch) => {
  return axios
    .get("http://localhost:8080/api/crawler", {
        params: {
          websiteUrl: url,
          scanDepth: depth
        }
      }
    ).then(payload => {
      dispatch({type: CRAWL_SUCCESS, payload})
    })
    .catch(err => {
      dispatch({type: CRAWL_FAILURE, err})
    })
    .finally(() => {
      dispatch({type: FINALLY}, null)
    })
}

export const quickScan = (url) => (dispatch) => {
  return axios
    .get("http://localhost:8080/api/quick-scan", {
      params: {
        websiteUrl: url
      }
    }).then(payload => {
      dispatch({type: QUICK_SCAN_SUCCESS, payload})
    })
    .catch(err => {
      dispatch({type: QUICK_SCAN_FAILURE, err})
    })
    .finally(() => {
      dispatch({type: FINALLY}, null)
    })
}

export const sqlAttackScan = (properties) => (dispatch) => {
  return axios
    .post("http://localhost:8080/api/sql-injection/start-scan", {
        ...properties
    }).then(payload => {
        dispatch({type: SQL_ATTACK_START_SCAN_SUCCESS, payload})
    })
}

export const sqlAttackStatus = (taskId) => (dispatch) => {
  return axios
    .get("http://localhost:8080/api/sql-injection/status/" + taskId)
    .then(payload => {
      dispatch({type: SQL_ATTACK_SCAN_STATUS, payload})
    })
}


export const sqlAttackData = (taskId) => (dispatch) => {
  return axios
    .get("http://localhost:8080/api/sql-injection/data/" + taskId)
    .then(payload => {
      dispatch({type: SQL_ATTACK_SCAN_DATA_SUCCESS, payload})
    })
    .finally(() => {
      dispatch({type: FINALLY}, null)
    })
}

export const sqlAttackLogs = (taskId) => (dispatch) => {
  return axios
    .get("http://localhost:8080/api/sql-injection/logs/" + taskId)
    .then(payload => {
      dispatch({type: SQL_ATTACK_SCAN_LOGS_SUCCESS, payload})
    })
    .finally(() => {
      dispatch({type: FINALLY}, null)
    })
}

export const loadForms = (url) => (dispatch) => {
  
  return axios
    .get("http://localhost:8080/api/data-collector/forms", {
      params: {
        websiteUrl: url
      }
    }).then(payload => {
      dispatch({type: LOAD_FORMS_SUCCESS, payload})
    })
    .catch(err => {
      dispatch({type: LOAD_FORM_FAILURE, err})
    })
    .finally(() => {
      dispatch({type: FINALLY}, null)
    })
}

export const spinner = (isLoading) => (dispatch) => {
  dispatch({type: LOADER, isLoading})
}