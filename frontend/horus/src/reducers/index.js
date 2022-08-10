import {
  CRAWL_SUCCESS,
  FINALLY,
  LOAD_FORMS_SUCCESS,
  LOADER,
  QUICK_SCAN_SUCCESS, SQL_ATTACK_SCAN_DATA_SUCCESS, SQL_ATTACK_SCAN_LOGS_SUCCESS, SQL_ATTACK_SCAN_STATUS,
  SQL_ATTACK_START_SCAN_SUCCESS, sqlAttackStatus
} from "../actions";

const initialState = {
  crawler: "",
  quickScanResult: null,
  isLoading: false
}

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case(CRAWL_SUCCESS):
      return {
        ...state,
        crawler: action.payload.data
      }
    case(FINALLY): {
      return {
        ...state,
        isLoading: false
      }
    }
    case(QUICK_SCAN_SUCCESS): {
      return {
        ...state,
        quickScanResult: action.payload.data
      }
    }
    case(LOAD_FORMS_SUCCESS): {
      return {
        ...state,
        forms: action.payload.data
      }
    }
    case(SQL_ATTACK_START_SCAN_SUCCESS): {
      return {
        ...state,
        sqlAttackStartScan: action.payload.data
      }
    }
    case(SQL_ATTACK_SCAN_STATUS): {
      return {
        ...state,
        sqlAttackScanStatus: action.payload.data
      }
    }
    case(SQL_ATTACK_SCAN_DATA_SUCCESS): {
      return {
        ...state,
        sqlAttackScanData: action.payload.data
      }
    }
    case(SQL_ATTACK_SCAN_LOGS_SUCCESS): {
      return {
        ...state,
        sqlAttackScanLogs: action.payload.data
      }
    }
    case(LOADER): {
      return {
        ...state,
        isLoading: action.isLoading
      }
    }
  }
  return state;
};

export default rootReducer;