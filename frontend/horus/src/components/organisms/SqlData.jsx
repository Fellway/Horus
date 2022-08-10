import DatabaseBanner from "../molecules/DatabaseBanner";
import SensitiveFields from "../molecules/SensitiveFields";
import DatabaseTables from "../molecules/DatabaseTables";
import DatabaseColumns from "../molecules/DatabaseColumns";
import React from "react";

export const SqlData = (data) => {
  return (
    <>
      <h3>Fetched data</h3>
      {data.data.map(dataRow => {
        switch (dataRow.type) {
          case(1):
            return <SensitiveFields value={dataRow.value}/>
          case(3):
            return <DatabaseBanner value={dataRow.value}/>
          case(13):
            return <DatabaseTables value={dataRow.value}/>
          case(14):
            return <DatabaseColumns value={dataRow.value}/>
        }
      })}
    </>
  )
}

export default SqlData;