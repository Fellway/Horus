package com.example.horus.sqlinjection.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ScanLogResponse {

    private final boolean success;
    private final List<Log> log;

    public class Log {
        private String time;
        private String level;
        private String message;

        public Log(String time, String level, String message) {
            this.time = time;
            this.level = level;
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
